package com.fronties.socialeventchat.profile.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import android.graphics.Bitmap
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception


@AndroidEntryPoint
class ViewProfileFragment : Fragment(R.layout.fragment_profile) {
    lateinit var binding: FragmentProfileBinding
    lateinit var profileViewModel: ProfileViewModel
    var ImageUri: Uri? = null
    var idRoom: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        profileViewModel.editMode = true


        binding.profileViewModel = profileViewModel

        // TODO: this is untidy, need to clean it up. Just a quick thing for demo
        profileViewModel.loadAll().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                idRoom = it[it.lastIndex].id
                binding.firstNameEt.setText(it[it.lastIndex].firstName)
                binding.lastNameEt.setText(it[it.lastIndex].lastName)
                binding.phoneNumberEt.setText(it[it.lastIndex].phoneNumber)
                if (it[it.lastIndex].profilePic == null) {
                    binding.profileIv.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireActivity(),
                            R.drawable.ic_person_24_24
                        )
                    )
                } else {
                    binding.profileIv.setImageBitmap(it[it.lastIndex].profilePic)
                }

            }
        })

        profileViewModel.listenerForProfileToEventFeed.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                updateProfile()
                Toast.makeText(context, "Profile updated!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_viewProfileFragment_to_eventListFragment)
            }
        }

        profileViewModel.listenerForProfileImage.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                launchSomeActivity.launch(intent)
            }
        }
    }

    private val launchSomeActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                ImageUri = result.data?.data
                binding.profileIv.setImageURI(ImageUri)
            }
        }

    fun updateProfile(): Unit {
        if (ImageUri != null){
            val bitmap = MediaStore.Images.Media.getBitmap(
                requireActivity().contentResolver, ImageUri
            )

            val file = persistImage(bitmap,"1",requireContext())
            file?.let{
                profileViewModel.updateProfile(file)
            }
        }
        else{
            profileViewModel.updateProfile(null)
        }

    }

    private fun persistImage(bitmap: Bitmap, name: String,context:Context): File? {
        val filesDir: File = context.filesDir
        val imageFile = File(filesDir, "$name.jpg")
        val os: OutputStream
        try {
            os = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, os)
            os.flush()
            os.close()
            return imageFile
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "Error writing bitmap", e)
        }
        return null
    }
}