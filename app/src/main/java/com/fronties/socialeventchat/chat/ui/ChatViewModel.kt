package com.fronties.socialeventchat.chat.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.application.session.AuthException
import com.fronties.socialeventchat.application.session.sessionManager.SessionManagerImpl
import com.fronties.socialeventchat.chat.model.*
import com.fronties.socialeventchat.chat.repo.ChatRepo
import com.fronties.socialeventchat.helperClasses.Event
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.Socket
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepo: ChatRepo,
    private val sessionManagerImpl: SessionManagerImpl,
    private val gson: Gson
) : ViewModel() {

    val textToSend = MutableLiveData<String>()
    private val _messageList = MutableLiveData<MutableList<MessageResponse>?>()
    val messageList: LiveData<MutableList<MessageResponse>?>
        get() = _messageList

    private val _listenerForTextSend = MutableLiveData<Event<Unit>>()
    val listenerForTextSend: LiveData<Event<Unit>>
        get() = _listenerForTextSend

    var eid = -1

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

    private val onReceivingPrevChats = { messages: Array<MessageResponse> ->
        _messageList.postValue(messages.toMutableList())
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
        chatRepo.getSocketIO()?.on(
            "lastMessages",
            chatRepo.onReceivingPrevChats(onReceivingPrevChats)
        )
    }

    fun sendText() {
        textToSend.value?.let { text ->
            val messageRequest = MessageRequest(
                eid = eid,
                uid = sessionManagerImpl.fetchUid(),
                text = text
            )
            chatRepo.getSocketIO()?.emit(
                "chatMessage",
                gson.toJson(messageRequest)
            )
            _listenerForTextSend.value = Event(Unit)
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
