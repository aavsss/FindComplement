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
import com.fronties.socialeventchat.MainActivity
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        DataBindingUtil.setContentView<FragmentRegisterBinding>(
            context as Activity, R.layout.fragment_register
        )

//        registerViewModel.passwordRegisterEtContent.observe(viewLifecycleOwner, Observer {
//
//        })
    }

}