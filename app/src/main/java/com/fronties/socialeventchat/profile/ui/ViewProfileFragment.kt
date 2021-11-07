package com.fronties.socialeventchat.profile.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
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
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@AndroidEntryPoint
class ViewProfileFragment : Fragment(R.layout.fragment_profile) {
    lateinit var binding: FragmentProfileBinding
    lateinit var profileViewModel: ProfileViewModel
    var ImageUri: Uri? = null
    var idRoom: Int? = null

    private val launchSomeActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                profileViewModel.setValueOfImageUri(result.data?.data)
                ImageUri = result.data?.data
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
        profileViewModel.loadById()?.observe(viewLifecycleOwner, {
            idRoom = it?.id
            binding.firstNameEt.setText(it?.firstName)
            binding.lastNameEt.setText(it?.lastName)
            binding.phoneNumberEt.setText(it?.phoneNumber)
            if (it?.profilePic.toString().isNullOrEmpty()) {
                binding.profileIv.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_person_24_24
                    )
                )
            } else {
                binding.profileIv.setImageURI(it?.profilePic)
            }
        })

        profileViewModel.listenerForProfileToEventFeed.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
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

        profileViewModel.profileImageUri.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                binding.profileIv.setImageURI(ImageUri)
            }
        }

        profileViewModel.profileInfoInvalid.observe(viewLifecycleOwner){
            Toast.makeText(context, "Enter a valid first and last name!", Toast.LENGTH_LONG).show()
        }

    }


}
