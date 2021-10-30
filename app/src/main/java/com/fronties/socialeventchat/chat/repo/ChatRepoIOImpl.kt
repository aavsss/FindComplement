package com.fronties.socialeventchat.chat.repo

import com.fronties.socialeventchat.application.session.AuthException
import com.fronties.socialeventchat.application.session.SessionManager
import com.fronties.socialeventchat.chat.api.ChatApi
import com.fronties.socialeventchat.chat.model.JoinRoom
import com.fronties.socialeventchat.chat.model.MessageResponse
import com.fronties.socialeventchat.chat.model.TempMessageResponse
import com.fronties.socialeventchat.event.api.EventApi
import com.fronties.socialeventchat.helperClasses.Constants.WS_URL
import com.fronties.socialeventchat.helperClasses.Resource
import com.google.gson.Gson
import com.google.gson.JsonElement
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject
import javax.inject.Inject
import kotlin.Exception

class ChatRepoIOImpl @Inject constructor(
    private val sessionManager: SessionManager,
    private val eventApi: EventApi,
    private val chatApi: ChatApi
) : ChatRepo {

    private var socket: Socket? = null
    private val gson = Gson()

    @Synchronized
    override fun establishWebSocketConnection() {
        try {
            socket = IO.socket(WS_URL)
            socket?.connect()
            socket?.on(Socket.EVENT_CONNECT, onConnect())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Synchronized
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

    override fun onConnect(): Emitter.Listener {
        val onConnect = Emitter.Listener {
            // Echo message
            println("....connected.......")
        }
        return onConnect
    }

    override fun onUpdateChat(callback: ((message: TempMessageResponse) -> Unit)): Emitter.Listener {
        val onUpdateChat = Emitter.Listener {
            val messageResponse = gson.fromJson(it[0].toString(), TempMessageResponse::class.java)
            callback(messageResponse)
        }
        return onUpdateChat
    }

    override fun onDestroy() {
        socket?.close()
    }

    override suspend fun getChats(eid: Int): MutableList<MessageResponse> {
        try {
            val response = eventApi.getChats(eid)

            if (response.isSuccessful && response.body() != null) {
                return response.body()!!
            }
            return mutableListOf()
        } catch (e: AuthException) {
            throw e
        } catch (e: Exception) {
            Resource.error(e.localizedMessage ?: "Error", null)
            throw e
        }
    }
}
