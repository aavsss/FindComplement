package com.fronties.socialeventchat.chat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fronties.socialeventchat.application.session.sessionManager.SessionManagerImpl
import com.fronties.socialeventchat.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify
import javax.inject.Inject

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    lateinit var binding: ActivityChatBinding
    lateinit var chatViewModel: ChatViewModel

    @Inject
    lateinit var sessionManagerImpl: SessionManagerImpl

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
            sessionManagerImpl.fetchUid()
        )

        chatViewModel.getChat(eid)
        binding.chatViewModel = chatViewModel
        binding.recyclerGchat.adapter = messageListAdapter

        val scrollToBottomObserver = ScrollToBottomObserver(
            binding.recyclerGchat,
            messageListAdapter,
            LinearLayoutManager(this)
        )

        binding.recyclerGchat.addOnLayoutChangeListener {
                _, _, _, _, bottom, _, _, _, oldBottom ->
            if (bottom < oldBottom) {
                binding.recyclerGchat.scrollBy(0, oldBottom - bottom)
            }
        }

        chatViewModel.messageList.observe(this) { list ->
            list?.let {
                messageListAdapter.chats = it
                // TODO - find a better way to notify adapter - I hate this
                messageListAdapter.notifyItemChanged(messageListAdapter.chats.lastIndex)
                messageListAdapter.notifyItemRangeInserted(messageListAdapter.chats.lastIndex, 1)
            }
        }

        chatViewModel.listenerForTextSend.observe(this) {
            it.getContentIfNotHandled()?.let {
                binding.editGchatMessage.text.clear()
            }
        }
    }

    override fun onDestroy() {
        chatViewModel.onDestroy()
        super.onDestroy()
    }
}
