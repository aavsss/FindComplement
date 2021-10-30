package com.fronties.socialeventchat.chat.repo

import com.fronties.socialeventchat.chat.model.MessageRequest
import com.fronties.socialeventchat.chat.model.MessageResponse
import com.fronties.socialeventchat.chat.model.TempMessageResponse
import io.socket.client.Socket
import io.socket.emitter.Emitter

interface ChatRepo {
    fun establishWebSocketConnection()
    fun getSocketIO(): Socket?
    fun onConnect(): Emitter.Listener
    fun joinRoom(eid: Int)
    fun onUpdateChat(callback: ((TempMessageResponse) -> Unit)): Emitter.Listener
    fun onDestroy()
    suspend fun getChats(eid: Int): MutableList<MessageResponse>
}
