package com.fronties.socialeventchat.profile.repo

import androidx.lifecycle.LiveData
import com.fronties.socialeventchat.helperClasses.Resource
import com.fronties.socialeventchat.profile.api.ProfileApi
import com.fronties.socialeventchat.profile.model.User
import com.fronties.socialeventchat.profile.room.ProfileDao
import com.fronties.socialeventchat.profile.room.ProfileEntity
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException

import javax.inject.Inject


class ProfileRepoImpl @Inject constructor(
    private val profileDao: ProfileDao,
    private val profileApi: ProfileApi,
    private val gson: Gson
) : ProfileRepo {
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

    override suspend fun updateProfile(rb: File?, user: User): User? {
        try {
            var filePart: MultipartBody.Part? = null
            if (rb != null) {
                filePart = MultipartBody.Part.createFormData(
                    "file", rb.name,
                    rb
                        .asRequestBody("image/*".toMediaTypeOrNull()),
                );
            }

            val userPart = MultipartBody.Part.createFormData("user", gson.toJson(user))

            val eventResponse = profileApi.updateProfile(1, filePart, userPart)
            if (eventResponse.isSuccessful && eventResponse.body() != null) {
                return eventResponse.body()
            }
            return null
        } catch (e: IOException) {
            Resource.error(e.localizedMessage ?: "IO Error", null)
            throw e
        } catch (e: HttpException) {
            Resource.error(e.localizedMessage ?: "HTTP Error", null)
            throw e
        }
    }
}
