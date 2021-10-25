package com.fronties.socialeventchat.chat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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

        val eid = intent.getIntExtra("eid", -1)

        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        chatViewModel.establishWebSocketConnection(eid)

        val messageListAdapter = MessageListAdapter()

        binding.chatViewModel = chatViewModel
        binding.recyclerGchat.adapter = messageListAdapter

        chatViewModel.messageList.observe(this) {
            messageListAdapter.submitList(it)
        }

        setContentView(view)
        setupAdapter()
    }

    private fun setupAdapter() {
        val adapter = MessageListAdapter()
        binding.recyclerGchat.adapter = adapter
    }

    override fun onDestroy() {
        chatViewModel.onDestroy()
        super.onDestroy()
    }
}
