package com.fronties.socialeventchat.authentication.login

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        DataBindingUtil.setContentView<FragmentLoginBinding>(
            context as Activity, R.layout.fragment_login
        )

    }
}