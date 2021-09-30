package com.fronties.socialeventchat.authentication.repo

interface AuthenticationRepo {
    suspend fun registerUser(username: String, password: String): Boolean
    suspend fun loginUser(username: String, password: String): Boolean
}