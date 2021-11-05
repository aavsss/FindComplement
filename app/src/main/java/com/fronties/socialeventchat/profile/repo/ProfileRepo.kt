package com.fronties.socialeventchat.profile.repo

import androidx.lifecycle.LiveData
import com.fronties.socialeventchat.profile.model.User
import com.fronties.socialeventchat.profile.room.ProfileEntity
import java.io.File

interface ProfileRepo {

    suspend fun updateProfile(
        firstName: String,
        lastName: String,
        phoneNumber: String
    )

    suspend fun saveUserProfile(profileEntity: ProfileEntity)

    suspend fun updateProfile(rb: File?, user: User): User?


    fun loadAllProfile(): LiveData<List<ProfileEntity>>
}
