package com.fronties.socialeventchat.authentication.login

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.application.CustomDialog
import com.fronties.socialeventchat.databinding.FragmentLoginBinding
import dagger.Provides
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
                findNavController().navigate(R.id.action_loginFragment_to_eventListFragment)
            }
        }

        loginViewModel.listenerForError.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { error ->
//                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
//                OpenDialogBox(error)
                var dialog = CustomDialog("login")
                dialog.show(activity?.supportFragmentManager!!,"customDialogFragment")
            }
        }


    }

    //Open a dialog box to show the error message

//    fun OpenDialogBox(message: String){
//        val builder = AlertDialog.Builder(context)
//        val inflater = layoutInflater
//        val dialogLayout = inflater.inflate(R.layout.error_screen,null)
//
//
//        with(builder){
//            setTitle("$message unsuccessful!")
//            setPositiveButton("OK"){dialog, which ->
//
//            }
////            setNegativeButton("Cancel"){dialog,which->}
//            setView(dialogLayout)
//            show()
//        }
//    }
}