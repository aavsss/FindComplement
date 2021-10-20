package com.fronties.socialeventchat.profile.repo

import androidx.lifecycle.LiveData
import com.fronties.socialeventchat.profile.room.ProfileDao
import com.fronties.socialeventchat.profile.room.ProfileEntity

import javax.inject.Inject


class ProfileRepoImpl @Inject constructor(
    private val profileDao: ProfileDao
): ProfileRepo {
    override suspend fun updateProfile(firstName: String, lastName: String, phoneNumber: String) {
//        TODO("Not yet implemented")
        // Basic logic: need to match the id of the user with the id in the profile table.
    }

    override suspend fun saveUserProfile(profileEntity: ProfileEntity) {
        profileDao.insertProfile(profileEntity)
    }

    override suspend fun loadAllProfile(): LiveData<List<ProfileEntity?>?>? {
//        println("Amir")
        return profileDao.loadAllProfile()
    }


}