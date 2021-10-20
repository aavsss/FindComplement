package com.fronties.socialeventchat.profile.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fronties.socialeventchat.MainActivity
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    lateinit var binding: FragmentProfileBinding
    lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        binding.profileViewModel = profileViewModel

        profileViewModel.listenerForNavToProfile.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let{
                findNavController().navigate(R.id.action_registerFragment_to_profileFragment)
            }
        }

        profileViewModel.listenerForProfileToEventFeed.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}