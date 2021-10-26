package com.fronties.socialeventchat.chat.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.application.session.AuthException
import com.fronties.socialeventchat.chat.model.MessageResponse
import com.fronties.socialeventchat.chat.repo.ChatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepo: ChatRepo
) : ViewModel() {

    val textToSend = MutableLiveData<String>()
    private val _messageList = MutableLiveData<MutableList<MessageResponse>>()
    val messageList: LiveData<MutableList<MessageResponse>>
        get() = _messageList

    init {
        chatRepo.establishWebSocketConnection()
    }

    private val onUpdateChat = { message: MessageResponse ->
        println(message)
        _messageList.value?.add(message)
        _messageList.notify() // diffcallback might ignore this
    }

    fun sendText() {
        textToSend.value?.let {
            chatRepo.sendText(it)
        }
    }

    fun onDestroy() {
        chatRepo.onDestroy()
    }

    fun establishWebSocketConnection(eid: Int) {
        chatRepo.joinRoom(eid)
        chatRepo.getSocketIO()?.on("updateChat", chatRepo.onUpdateChat(onUpdateChat))
//        chatRepo.getSocketIO().on("sendText", chatRepo.sendText("sendText"))
    }

    fun getChat(eid: Int) {
        viewModelScope.launch {
            val chats = try {
                chatRepo.getChats(eid)
            } catch (e: AuthException) {
                return@launch
            } catch (e: Exception) {
                return@launch
            }
            _messageList.value = chats
        }
    }
}
