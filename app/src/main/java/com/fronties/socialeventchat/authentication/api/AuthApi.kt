package com.fronties.socialeventchat.authentication.api

import com.fronties.socialeventchat.authentication.model.AuthRequest
import com.fronties.socialeventchat.authentication.model.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface AuthApi {

    @GET("/api/users/")
    suspend fun registerUser(
        @Body authRequest: AuthRequest
    ): Response<AuthResponse>

    @GET("/api/users/login/")
    suspend fun loginUser(
        @Body authRequest: AuthRequest
    ): Response<AuthResponse>
}
