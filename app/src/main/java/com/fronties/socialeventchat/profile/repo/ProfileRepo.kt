package com.fronties.socialeventchat.profile.repo

interface ProfileRepo {

    suspend fun updateProfile(
        firstName: String,
        lastName: String,
        phoneNumber: String
    )
}
