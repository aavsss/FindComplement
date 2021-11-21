package com.fronties.socialeventchat.chat.model

data class MessageResponse(
    val eid: Int,
    val uid: Int,
    val text: String,
    val name: String,
    val timestamp: String
)
