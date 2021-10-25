package com.fronties.socialeventchat.chat.repo

import com.fronties.socialeventchat.application.session.SessionManager
import com.fronties.socialeventchat.chat.model.JoinRoom
import com.fronties.socialeventchat.chat.model.MessageResponse
import com.fronties.socialeventchat.helperClasses.Constants.WS_URL
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.lang.Exception
import javax.inject.Inject

class ChatRepoIOImpl @Inject constructor(
    private val sessionManager: SessionManager
) : ChatRepo {

    lateinit var socket: Socket
    private val gson = Gson()

    override fun establishWebSocketConnection() {
        try {
            socket = IO.socket(WS_URL)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        socket.connect()
        socket.on(Socket.EVENT_CONNECT, onConnect())
    }

    override fun getSocketIO(): Socket {
        return socket
    }

    override fun joinRoom(eid: Int) {
        val joinRoom = JoinRoom(
            username = sessionManager.fetchUid(),
            room = eid
        )
        socket.emit("joinRoom", gson.toJson(joinRoom))
    }

    override fun sendText(message: String) {
        TODO("Not yet implemented")
    }

    override fun onConnect(): Emitter.Listener {
        val onConnect = Emitter.Listener {
        }
        return onConnect
    }

    override fun onUpdateChat(callback: ((message: MessageResponse) -> Unit)): Emitter.Listener {
        val onUpdateChat = Emitter.Listener {
            val messageResponse = it[0] as MessageResponse
            callback(messageResponse)
        }
        return onUpdateChat
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}
