package com.fronties.socialeventchat.chat.model

data class MessageResponse(
    val eid: Int,
    val uid: Int,
    val text: String,
    val created_at: String,
    val firstname: String
)
