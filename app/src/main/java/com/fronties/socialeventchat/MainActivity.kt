package com.fronties.socialeventchat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fronties.socialeventchat.databinding.ActivityMainBinding
import com.fronties.socialeventchat.databinding.FragmentEventDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var tempBinding: FragmentEventDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        //region test
        tempBinding = FragmentEventDetailBinding.inflate(layoutInflater)
        val tempView = tempBinding.root

//        setSupportActionBar(tempBinding.toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)
//        tempBinding.toolbarLayout.title = title
        //endregion

        setContentView(tempView)
    }
}
