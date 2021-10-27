package com.fronties.socialeventchat.chat.model

data class MessageRequest(
    val eid: Int,
    val senderid: Int,
    val name: String,
    val text: String
)
