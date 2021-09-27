package com.fronties.socialeventchat.authentication.repo

interface AuthenticationRepo {
    fun registerUser(username: String, password: String)
    fun loginUser(username: String, password: String)
}