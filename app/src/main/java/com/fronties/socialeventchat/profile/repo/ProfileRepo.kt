package com.fronties.socialeventchat.profile.repo

import androidx.lifecycle.LiveData
import com.fronties.socialeventchat.profile.room.ProfileEntity

interface ProfileRepo {

    suspend fun updateProfile(
        firstName: String,
        lastName: String,
        phoneNumber: String
    )

    suspend fun saveUserProfile(profileEntity: ProfileEntity)

    suspend fun loadAllProfile(): LiveData<List<ProfileEntity?>?>?
}
