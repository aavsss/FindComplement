package com.fronties.socialeventchat.chat.model

data class MessageRequest(
    val eid: String,
    val name: String,
    val senderid: String,
    val text: String
)
