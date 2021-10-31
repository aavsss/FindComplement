package com.fronties.socialeventchat.chat.model

data class MessageRequest(
    val eid: Int,
    val uid: Int,
    val text: String,
)
