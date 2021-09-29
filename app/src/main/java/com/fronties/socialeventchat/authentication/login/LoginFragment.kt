package com.fronties.socialeventchat.authentication.login

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    lateinit var binding: FragmentLoginBinding
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container
        ,false)

        return binding.root
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = loginViewModel
        loginViewModel.listenerForNavToRegister.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }

        loginViewModel.listenerForNavToEventFeed.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(R.id.action_loginFragment_to_eventDetailFragment)
            }
        }


    }
}