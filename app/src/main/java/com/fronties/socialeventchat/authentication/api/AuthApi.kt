package com.fronties.socialeventchat.authentication.api

import com.fronties.socialeventchat.authentication.model.AuthResponse
import retrofit2.Response

interface AuthApi {
    fun registerUser(): Response<AuthResponse>
    fun loginUser(): Response<AuthResponse>
}