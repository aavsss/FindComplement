package com.fronties.socialeventchat.profile.api

import com.fronties.socialeventchat.profile.model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*


interface ProfileApi {

    @PUT("/api/users/{id}")
    suspend fun updateUser(
        @Path("id") uid: Int
    )

    @Multipart
    @PUT("/api/users/{id}")
    suspend fun updateProfile(
        @Path("id") uid: Int,
        @Part filePart: MultipartBody.Part?,
        @Part user: MultipartBody.Part
    ): Response<User>


//    @POST("/send")
//    fun upload(@Body bytes: TypedInput?, cb: Callback<String?>?)
}
