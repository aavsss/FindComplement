package com.fronties.socialeventchat.chat.repo

import com.fronties.socialeventchat.chat.model.JoinRoomResponse
import com.fronties.socialeventchat.chat.model.MessageResponse
import io.socket.client.Socket
import io.socket.emitter.Emitter

interface ChatRepo {
    fun establishWebSocketConnection()
    fun getSocketIO(): Socket?
    fun onConnect(callback: ((JoinRoomResponse?) -> Unit)): Emitter.Listener
    fun joinRoom(eid: Int)
    fun onUpdateChat(callback: ((MessageResponse) -> Unit)): Emitter.Listener
    fun onReceivingPrevChats(callback: (Array<MessageResponse>) -> Unit): Emitter.Listener
    fun onDestroy()
    suspend fun getChats(eid: Int): MutableList<MessageResponse>
}
