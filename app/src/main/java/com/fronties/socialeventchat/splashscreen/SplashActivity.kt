package com.fronties.socialeventchat.splashscreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fronties.socialeventchat.MainActivity
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.authentication.AuthActivity
import com.fronties.socialeventchat.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        viewModel.checkLogInStatus()
    }

    override fun onResume() {
        super.onResume()
        viewModel.isLoggedIn.observe(this) {
            it.getContentIfNotHandled()?.let { isLoggedIn ->
                Handler(Looper.getMainLooper()).postDelayed({
                    if (isLoggedIn) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this, AuthActivity::class.java)
                        startActivity(intent)
                    }
                    finish()
                }, 1000)
            }
        }
    }
}
