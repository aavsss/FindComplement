package com.fronties.socialeventchat.authentication.repo

interface AuthenticationRepo {
    suspend fun registerUser(email: String, password: String): Boolean
    suspend fun loginUser(email: String, password: String): Boolean
}
