package com.fronties.socialeventchat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fronties.socialeventchat.databinding.ActivityMainBinding
import com.fronties.socialeventchat.databinding.FragmentEventDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        // connect nav controller and our nav bar
        val navController = findNavController(R.id.nav_host_fragment_main)
        binding.bottomNavBar.setupWithNavController(navController)
    }
}
