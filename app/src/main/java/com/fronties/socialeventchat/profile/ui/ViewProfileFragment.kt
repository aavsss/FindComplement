package com.fronties.socialeventchat.profile.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fronties.socialeventchat.MainActivity
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewProfileFragment : Fragment(R.layout.fragment_profile) {
    lateinit var binding: FragmentProfileBinding
    lateinit var profileViewModel: ProfileViewModel

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
            binding.firstNameEt.setText(it[it.lastIndex].firstName)
            binding.lastNameEt.setText(it[it.lastIndex].lastName)
            binding.phoneNumberEt.setText(it[it.lastIndex].phoneNumber)
        })

        profileViewModel.listenerForProfileToEventFeed.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, "Profile updated!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_viewProfileFragment_to_eventListFragment)
            }
        }
    }
}