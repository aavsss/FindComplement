package com.fronties.socialeventchat.chat.repo

import com.fronties.socialeventchat.helperClasses.Constants.WS_RUL
import okhttp3.*
import okio.ByteString
import javax.inject.Inject

class ChatRepoImpl @Inject constructor() : ChatRepo {

    private val request = Request.Builder().url(WS_RUL).build()

    override fun establishWebSocketConnection() {
        val webSocketListener = object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
            }
        }
        val socketOkHTTPClient = OkHttpClient.Builder().build()
        val webSocket = socketOkHTTPClient.newWebSocket(request, webSocketListener)
        socketOkHTTPClient.dispatcher.executorService.shutdown()
    }
}
