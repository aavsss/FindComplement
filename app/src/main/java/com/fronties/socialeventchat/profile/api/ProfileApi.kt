package com.fronties.socialeventchat.profile.api

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ProfileApi {

    @PUT("/api/users/{id}")
    suspend fun updateUser(
        @Path("id") uid: Int
    )

    @POST("/api/users/{id}/image")
    suspend fun uploadImage(
        @Path("id") uid: Int,
        @Body bytes: ByteArray?
    )

    @POST("/send")
    fun upload(@Body bytes: TypedInput?, cb: Callback<String?>?)
}
