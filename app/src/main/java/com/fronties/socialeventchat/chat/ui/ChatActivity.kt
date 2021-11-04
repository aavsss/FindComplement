package com.fronties.socialeventchat.chat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fronties.socialeventchat.application.session.SessionManager
import com.fronties.socialeventchat.chat.model.MessageResponse
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
        chatViewModel.eid = eid
        chatViewModel.establishWebSocketConnection()

        val messageListAdapter = MessageListAdapter(
            sessionManager.fetchUid()
        )

        chatViewModel.getChat(eid)
        binding.chatViewModel = chatViewModel
        binding.recyclerGchat.adapter = messageListAdapter
//        messageListAdapter.chats = mutableListOf(
//            MessageResponse(1, 1, 1, "Asim", "Now", "Yesterday"),
//            MessageResponse(1, 5, 1, "Amir", "Hello again", "Now"),
//            MessageResponse(1, 1, 1, "Asim", "Now", "Yesterday"),
//            MessageResponse(1, 5, 1, "Amir", "Hello again", "Now"),
//            MessageResponse(1, 5, 1, "Amir", "Hello again", "Now"),
//            MessageResponse(1, 1, 1, "Asim", "Now", "Yesterday"),
//            MessageResponse(1, 5, 1, "Amir", "Hello again", "Now"),
//            MessageResponse(1, 1, 1, "Asim", "Now", "Yesterday"),
//            MessageResponse(1, 5, 1, "Amir", "Hello again", "Now"),
//            MessageResponse(1, 1, 1, "Asim", "Now", "Yesterday"),
//
//        )

        ScrollToBottomObserver(
            binding.recyclerGchat,
            messageListAdapter,
            LinearLayoutManager(this)
        )

        chatViewModel.messageList.observe(this) { list ->
            list?.let {
                messageListAdapter.chats = it
                // TODO - find a better way to notify adapter - I hate this
                messageListAdapter.notifyItemChanged(messageListAdapter.chats.lastIndex)
                messageListAdapter.notifyItemRangeInserted(messageListAdapter.chats.lastIndex, 1)
            }
        }
    }

    override fun onDestroy() {
        chatViewModel.onDestroy()
        super.onDestroy()
    }
}
