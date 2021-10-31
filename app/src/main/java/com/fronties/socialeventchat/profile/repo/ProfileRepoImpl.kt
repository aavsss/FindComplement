package com.fronties.socialeventchat.profile.repo

import androidx.lifecycle.LiveData
import com.fronties.socialeventchat.event.model.SocialEvents
import com.fronties.socialeventchat.helperClasses.Resource
import com.fronties.socialeventchat.profile.api.ProfileApi
import com.fronties.socialeventchat.profile.room.ProfileDao
import com.fronties.socialeventchat.profile.room.ProfileEntity
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject


class ProfileRepoImpl @Inject constructor(
    private val profileDao: ProfileDao,
    private val profileApi: ProfileApi
): ProfileRepo {
    override suspend fun updateProfile(firstName: String, lastName: String, phoneNumber: String) {
//        TODO("Not yet implemented")
        // Basic logic: need to match the id of the user with the id in the profile table.
    }

    override suspend fun saveUserProfile(profileEntity: ProfileEntity) {
        profileDao.insertProfile(profileEntity)
    }

    override fun loadAllProfile(): LiveData<List<ProfileEntity>> {
        return profileDao.loadAllProfile()
    }

    override suspend fun uploadImage(rb: RequestBody) {
//        rb
        try {
            profileApi.uploadImage(1,rb)
//            if (eventResponse.isSuccessful && eventResponse.body() != null) {
//                return eventResponse.body()!!
//            }

        } catch (e: IOException) {
            Resource.error(e.localizedMessage ?: "IO Error", null)
            throw e
        } catch (e: HttpException) {
            Resource.error(e.localizedMessage ?: "HTTP Error", null)
            throw e
        }

    }
}