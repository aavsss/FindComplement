package com.fronties.socialeventchat.chat.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.application.session.AuthException
import com.fronties.socialeventchat.application.session.SessionManager
import com.fronties.socialeventchat.chat.model.MessageRequest
import com.fronties.socialeventchat.chat.model.MessageResponse
import com.fronties.socialeventchat.chat.repo.ChatRepo
import com.fronties.socialeventchat.helperClasses.Event
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepo: ChatRepo,
    private val sessionManager: SessionManager
) : ViewModel() {

    val textToSend = MutableLiveData<String>()
    private val _messageList = MutableLiveData<MutableList<MessageResponse>>()
    val messageList: LiveData<MutableList<MessageResponse>>
        get() = _messageList

    val tempListener = MutableLiveData<Event<Unit>>()
    var eid = -1
    val gson = Gson()

    private val onUpdateChat = { message: MessageResponse ->
//        println(message)
        val tempList = _messageList.value
        tempList?.add(message)
        _messageList.value = tempList
    }

    init {
        chatRepo.establishWebSocketConnection()
        chatRepo.getSocketIO()?.on(
            "message",
            chatRepo.onUpdateChat(onUpdateChat)
        )
    }

    fun sendText() {
        textToSend.value?.let { message ->
            val messageRequest = MessageRequest(
                eid,
                sessionManager.fetchUid(),
                sessionManager.fetchUName(),
                message
            )
            chatRepo.getSocketIO()?.emit("chatMessage", gson.toJson(messageRequest))
        }
    }

    fun onDestroy() {
        chatRepo.onDestroy()
    }

    fun establishWebSocketConnection() {
        chatRepo.joinRoom(eid)
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
