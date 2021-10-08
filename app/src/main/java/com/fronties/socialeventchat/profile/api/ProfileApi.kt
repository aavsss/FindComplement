package com.fronties.socialeventchat.profile.api

import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileApi {

    @PUT("/api/users/{id}")
    suspend fun updateUser(
        @Path("id") uid: Int
    )
}
