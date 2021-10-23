package com.fronties.socialeventchat.chat.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.fronties.socialeventchat.authentication.register.RegisterViewModel
import com.fronties.socialeventchat.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    lateinit var binding: ActivityChatBinding
    lateinit var chatViewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root

        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        chatViewModel.establishConnection()

        setContentView(view)
        setupAdapter()
    }

    private fun setupAdapter() {
        val adapter = MessageListAdapter()
        binding.recyclerGchat.adapter = adapter
    }
}