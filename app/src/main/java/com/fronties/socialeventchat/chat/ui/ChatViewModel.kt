package com.fronties.socialeventchat.chat.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fronties.socialeventchat.chat.model.MessageResponse
import com.fronties.socialeventchat.chat.repo.ChatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepo: ChatRepo
) : ViewModel() {

    val textToSend = MutableLiveData<String>()

    private val onUpdateChat = { message: MessageResponse ->
        println(message)
    }

    init {
        chatRepo.establishWebSocketConnection()
        chatRepo.getSocketIO().on("updateChat", chatRepo.onUpdateChat(onUpdateChat))
    }

    fun sendText() {
        textToSend.value?.let {
            chatRepo.sendText(it)
        }
    }

    fun onDestroy() {
        chatRepo.onDestroy()
    }
}
