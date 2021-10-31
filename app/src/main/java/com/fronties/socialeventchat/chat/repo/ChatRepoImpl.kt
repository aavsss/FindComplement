package com.fronties.socialeventchat.chat.repo

import com.fronties.socialeventchat.chat.model.MessageRequest
import com.fronties.socialeventchat.chat.model.MessageResponse
import com.google.gson.Gson
import io.socket.client.Socket
import io.socket.emitter.Emitter
import okhttp3.*
import okio.ByteString
import javax.inject.Inject
import javax.inject.Singleton

//class ChatRepoImpl @Inject constructor(
//    private val socketOkHTTPClient: OkHttpClient
//) : ChatRepo {
//
//    @Inject
//    lateinit var request: Request
//    lateinit var webSocket: WebSocket
//
//
//    override fun establishWebSocketConnection() {
//        val webSocketListener = object : WebSocketListener() {
//
//            override fun onOpen(webSocket: WebSocket, response: Response) {
//                super.onOpen(webSocket, response)
//            }
//
//            override fun onMessage(webSocket: WebSocket, text: String) {
//                super.onMessage(webSocket, text)
//            }
//
//            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
//                super.onMessage(webSocket, bytes)
//            }
//
//            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
//                super.onClosing(webSocket, code, reason)
//            }
//
//            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
//                super.onClosed(webSocket, code, reason)
//            }
//
//            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
//                super.onFailure(webSocket, t, response)
//            }
//        }
//        webSocket = socketOkHTTPClient.newWebSocket(request, webSocketListener)
//        socketOkHTTPClient.dispatcher.executorService.shutdown()
//    }
//
//    override fun getSocketIO(): Socket {
//        TODO("Not yet implemented")
//    }
//
//    override fun sendText(message: String) {
//        val messageRequest = MessageRequest(
//            "1",
//            "1",
//            message
//        )
//        webSocket.send(Gson().toJson(messageRequest))
//    }
//
//
//    override fun onConnect(): Emitter.Listener {
//        TODO("Not yet implemented")
//    }
//
//    override fun joinRoom() {
//        TODO("Not yet implemented")
//    }
//
//    override fun onUpdateChat(callback: (MessageResponse) -> Unit): Emitter.Listener {
//        TODO("Not yet implemented")
//    }
//
//    override fun onDestroy() {
////        webSocket.cancel() // Already handled?
//    }
//}