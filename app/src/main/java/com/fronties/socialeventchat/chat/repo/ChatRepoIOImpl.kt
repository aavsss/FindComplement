package com.fronties.socialeventchat.chat.repo

import com.fronties.socialeventchat.application.session.AuthException
import com.fronties.socialeventchat.application.session.SessionManager
import com.fronties.socialeventchat.chat.api.ChatApi
import com.fronties.socialeventchat.chat.model.JoinRoom
import com.fronties.socialeventchat.chat.model.MessageResponse
import com.fronties.socialeventchat.event.api.EventApi
import com.fronties.socialeventchat.helperClasses.Constants.WS_URL
import com.fronties.socialeventchat.helperClasses.Resource
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import javax.inject.Inject
import kotlin.Exception

class ChatRepoIOImpl @Inject constructor(
    private val sessionManager: SessionManager,
    private val eventApi: EventApi,
    private val chatApi: ChatApi
) : ChatRepo {

    private var socket: Socket? = null
    private val gson = Gson()

    override fun establishWebSocketConnection() {
        try {
            socket = IO.socket(WS_URL)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        socket?.connect()
        socket?.on(Socket.EVENT_CONNECT, onConnect())
    }

    override fun getSocketIO(): Socket? {
        return socket
    }

    override fun joinRoom(eid: Int) {
        val joinRoom = JoinRoom(
            username = sessionManager.fetchUid(),
            room = eid
        )
        socket?.emit("joinRoom", gson.toJson(joinRoom))
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
//        TODO("Not yet implemented")
    }

    override suspend fun getChats(eid: Int): MutableList<MessageResponse> {
        try {
//            val response = eventApi.getChat(eid)

//            if (response.isSuccessful && response.body() != null) {
//                return response.body()!!
//            }
//            return mutableListOf()
            return mutableListOf(
                MessageResponse("1", "1", "1", "Hello", "Now"),
                MessageResponse("1", "5", "1", "Hello again", "Yesterday")
            )
        } catch (e: AuthException) {
            throw e
        } catch (e: Exception) {
            Resource.error(e.localizedMessage ?: "Error", null)
            throw e
        }
    }
}
