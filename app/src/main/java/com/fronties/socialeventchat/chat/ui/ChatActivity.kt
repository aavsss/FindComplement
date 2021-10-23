package com.fronties.socialeventchat.chat.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fronties.socialeventchat.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        setupAdapter()
    }

    private fun setupAdapter() {
        val adapter = MessageListAdapter()
        binding.recyclerGchat.adapter = adapter
    }
}