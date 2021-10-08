package com.fronties.socialeventchat.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        viewModel.checkLogInStatus()
        viewModel.isLoggedIn.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { isLoggedIn ->
                if (isLoggedIn) {
                    findNavController().navigate(R.id.action_splashFragment_to_eventListFragment)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }
            }
        }
    }
}
