package com.fronties.socialeventchat.chat.model

data class MessageRequest(
    val eid: String,
    val senderid: String,
    val message: String
)
