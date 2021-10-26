package com.fronties.socialeventchat.chat.repo

import com.fronties.socialeventchat.chat.model.MessageResponse
import io.socket.client.Socket
import io.socket.emitter.Emitter

interface ChatRepo {
    fun establishWebSocketConnection()
    fun getSocketIO(): Socket?
    fun sendText(message: String)
    fun onConnect(): Emitter.Listener
    fun joinRoom(eid: Int)
    fun onUpdateChat(callback: ((MessageResponse) -> Unit)): Emitter.Listener
    fun onDestroy()
    suspend fun getChats(eid: Int): MutableList<MessageResponse>
}
