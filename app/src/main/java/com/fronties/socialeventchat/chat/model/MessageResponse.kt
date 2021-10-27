package com.fronties.socialeventchat.chat.model

data class MessageResponse(
    val eid: Int,
    val senderid: Int,
    val mid: Int,
    val name: String,
    val text: String,
    val createdAt: String
)
