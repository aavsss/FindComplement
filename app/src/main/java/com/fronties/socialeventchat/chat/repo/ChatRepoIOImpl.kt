package com.fronties.socialeventchat.chat.repo

import com.fronties.socialeventchat.application.session.AuthException
import com.fronties.socialeventchat.application.session.sessionManager.SessionManager
import com.fronties.socialeventchat.application.session.sessionManager.SessionManagerImpl
import com.fronties.socialeventchat.chat.api.ChatApi
import com.fronties.socialeventchat.chat.model.JoinRoom
import com.fronties.socialeventchat.chat.model.JoinRoomResponse
import com.fronties.socialeventchat.chat.model.MessageResponse
import com.fronties.socialeventchat.event.api.EventApi
import com.fronties.socialeventchat.helperClasses.Constants.WS_URL
import com.fronties.socialeventchat.helperClasses.Resource
import com.fronties.socialeventchat.profile.room.ProfileDao
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import javax.inject.Inject
import kotlin.Exception

class ChatRepoIOImpl @Inject constructor(
    private val sessionManager: SessionManager,
    private val eventApi: EventApi,
    private val chatApi: ChatApi,
    private val gson: Gson
) : ChatRepo {

    private var socket: Socket? = null

    @Synchronized
    override fun establishWebSocketConnection() {
        try {
            socket = IO.socket(WS_URL)
            socket?.connect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Synchronized
    override fun getSocketIO(): Socket? {
        return socket
    }

    @Synchronized
    override fun joinRoom(eid: Int) {
        val joinRoom = JoinRoom(
            uid = 1, // TODO - change ID
            eid = eid
        )
        println(gson.toJson(joinRoom))
        socket?.emit("joinRoom", gson.toJson(joinRoom))
    }

    @Synchronized
    override fun onConnect(callback: (message: JoinRoomResponse?) -> Unit): Emitter.Listener {
        val onConnect = Emitter.Listener {
            // Echo message
//            val joinRoomResponse = gson.fromJson(it[0].toString(), JoinRoomResponse::class.java)
//            callback(joinRoomResponse)
        }
        return onConnect
    }

    @Synchronized
    override fun onReceivingPrevChats(callback: (Array<MessageResponse>) -> Unit): Emitter.Listener {
        val onReceivingPrevChats = Emitter.Listener {
            val messagesResponse = gson.fromJson(it[0].toString(), Array<MessageResponse>::class.java)
            callback(messagesResponse)
        }
        return onReceivingPrevChats
    }

    @Synchronized
    override fun onUpdateChat(callback: ((message: MessageResponse) -> Unit)): Emitter.Listener {
        val onUpdateChat = Emitter.Listener {
            val messageResponse = gson.fromJson(it[0].toString(), MessageResponse::class.java)
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
