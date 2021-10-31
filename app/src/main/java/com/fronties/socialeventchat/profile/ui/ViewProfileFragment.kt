package com.fronties.socialeventchat.profile.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream


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
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        profileViewModel.editMode = true


        binding.profileViewModel = profileViewModel

        // TODO: this is untidy, need to clean it up. Just a quick thing for demo
        profileViewModel.loadAll().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()){
                idRoom = it[it.lastIndex].id
                binding.firstNameEt.setText(it[it.lastIndex].firstName)
                binding.lastNameEt.setText(it[it.lastIndex].lastName)
                binding.phoneNumberEt.setText(it[it.lastIndex].phoneNumber)
                if(it[it.lastIndex].profilePic == null){
                    binding.profileIv.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_person_24_24))
                }
                else{
                    binding.profileIv.setImageBitmap(it[it.lastIndex].profilePic)
                }

            }
        })

        profileViewModel.listenerForProfileToEventFeed.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, "Profile updated!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_viewProfileFragment_to_eventListFragment)
            }
        }

        var launchSomeActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                ImageUri = result.data?.data
                binding.profileIv.setImageURI(ImageUri)

                var bitmap = MediaStore.Images.Media.getBitmap(
                    requireActivity().contentResolver, ImageUri
                )
                profileViewModel._profileImage.value = bitmap

                val outputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 70, outputStream)

                var amir = outputStream.toByteArray()

                val body: RequestBody = RequestBody.create("application/octet-stream".toMediaTypeOrNull(), amir)

                profileViewModel.uploadImage(body)
                println("amir" + amir)
            }
        }

        profileViewModel.listenerForProfileImage.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                launchSomeActivity.launch(intent)
            }
        }

    }
}