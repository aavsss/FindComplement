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
    }

    override fun saveUserProfile(profileEntity: ProfileEntity) {
//        val eachProfile = ProfileEntity(firstName!!, lastName!!, phoneNumber!!)


        profileDao.insertProfile(profileEntity)
//        ProfileExecutor.getInstance()?.diskIO()?.execute(Runnable {
//            if (mProfileId == DEFAULT_GOAL_ID) {
//                mProfileDatabase?.profileDao()?.insertProfile(eachProfile)
//            } else {
//                eachProfile.id = mProfileId
//                mProfileDatabase?.profileDao()?.updateProfile(eachProfile)
//            }
//
//        })
    }

    override suspend fun loadAllProfile(): LiveData<List<ProfileEntity?>?>? {
//        println("Amir")
        return profileDao.loadAllProfile()
    }


}