package com.fronties.socialeventchat.chat.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.application.session.AuthException
import com.fronties.socialeventchat.application.session.sessionManager.SessionManagerImpl
import com.fronties.socialeventchat.chat.model.*
import com.fronties.socialeventchat.chat.repo.ChatRepo
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.Socket
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepo: ChatRepo,
    private val sessionManagerImpl: SessionManagerImpl
) : ViewModel() {

    val textToSend = MutableLiveData<String>()
    private val _messageList = MutableLiveData<MutableList<MessageResponse>?>()
    val messageList: LiveData<MutableList<MessageResponse>?>
        get() = _messageList

    var eid = -1
    private val gson = Gson()

    private val onConnect = { joinRoomResponse: JoinRoomResponse? ->
        if (joinRoomResponse != null) {
            val chats = joinRoomResponse.messages
            _messageList.postValue(chats)
        }
    }

    private val onUpdateChat = { message: MessageResponse ->
        var tempList = _messageList.value
        if (tempList == null) {
            tempList = mutableListOf()
        }
        tempList.add(message)
        _messageList.postValue(tempList)
    }

    init {
        chatRepo.establishWebSocketConnection()
        chatRepo.getSocketIO()?.on(
            Socket.EVENT_CONNECT,
            chatRepo.onConnect(onConnect)
        )
        chatRepo.getSocketIO()?.on(
            "message",
            chatRepo.onUpdateChat(onUpdateChat)
        )
    }

    fun sendText() {
        textToSend.value?.let { text ->
            val messageRequest = MessageRequest(
                eid = eid,
                uid = sessionManagerImpl.fetchUid(),
                text = text
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
//            _messageList.value = chats
        }
    }
}
