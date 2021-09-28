package com.fronties.socialeventchat.authentication.register

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fronties.socialeventchat.MainActivity
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    lateinit var binding: FragmentRegisterBinding
    lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.registerViewModel = registerViewModel

        registerViewModel.listenerForNavToMainScreen.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(R.id.action_registerFragment_to_profileFragment)
            }
        }

//        registerViewModel.passwordRegisterEtContent.observe(viewLifecycleOwner, Observer {
//
//        })
    }

}