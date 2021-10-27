package com.fronties.socialeventchat.chat.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fronties.socialeventchat.application.session.SessionManager
import com.fronties.socialeventchat.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    lateinit var binding: ActivityChatBinding
    lateinit var chatViewModel: ChatViewModel

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val eid = intent.getIntExtra("eid", -1)

        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
//        chatViewModel.establishWebSocketConnection(eid)
        val messageListAdapter = MessageListAdapter(
            sessionManager.fetchUid()
        )
        chatViewModel.getChat(eid)
        binding.chatViewModel = chatViewModel
        binding.recyclerGchat.adapter = messageListAdapter

        chatViewModel.messageList.observe(this) {
            messageListAdapter.chats = it
        }

        chatViewModel.tempListener.observe(this) {
            messageListAdapter.chats
        }
    }

    override fun onDestroy() {
        chatViewModel.onDestroy()
        super.onDestroy()
    }
}
