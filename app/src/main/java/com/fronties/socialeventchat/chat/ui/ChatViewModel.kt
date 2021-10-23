package com.fronties.socialeventchat.chat.ui

import androidx.lifecycle.ViewModel
import com.fronties.socialeventchat.chat.repo.ChatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepo: ChatRepo
) : ViewModel() {

    fun establishConnection() {
        chatRepo.establishWebSocketConnection()
    }
}
