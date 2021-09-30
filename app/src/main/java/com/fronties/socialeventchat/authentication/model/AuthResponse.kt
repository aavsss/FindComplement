package com.fronties.socialeventchat.authentication.model

data class AuthResponse(
    val uid: Int,
    val email: String?,
    val token: String?
)
