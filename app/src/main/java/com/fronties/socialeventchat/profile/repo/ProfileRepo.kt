package com.fronties.socialeventchat.profile.repo

import androidx.lifecycle.LiveData
import com.fronties.socialeventchat.profile.room.ProfileEntity
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface ProfileRepo {

    suspend fun updateProfile(
        firstName: String,
        lastName: String,
        phoneNumber: String
    )

    suspend fun saveUserProfile(profileEntity: ProfileEntity)

    suspend fun uploadImage(rb: RequestBody)


    fun loadAllProfile(): LiveData<List<ProfileEntity>>
}
