package com.fronties.socialeventchat.profile.api

import com.fronties.socialeventchat.event.model.EventResponse
import com.fronties.socialeventchat.profile.repo.profileResponse
import okhttp3.RequestBody
import okhttp3.Response
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
        @Body bytes: RequestBody
    )


//    @POST("/send")
//    fun upload(@Body bytes: TypedInput?, cb: Callback<String?>?)
}
