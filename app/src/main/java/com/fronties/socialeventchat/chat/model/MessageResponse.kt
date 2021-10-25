package com.fronties.socialeventchat.chat.model

data class MessageResponse(
    val eid: String,
    val senderid: String,
    val mid: String,
    val text: String,
    val createdAt: String
)
