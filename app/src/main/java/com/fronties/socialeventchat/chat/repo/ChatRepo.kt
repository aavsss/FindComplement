package com.fronties.socialeventchat.chat.repo

interface ChatRepo {
    fun establishWebSocketConnection()
    fun sendText(message: String)
    fun onDestroy()
}
