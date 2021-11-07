package com.fronties.socialeventchat.profile.repo

import android.net.Uri
import androidx.lifecycle.LiveData
import com.fronties.socialeventchat.application.session.SessionManager
import com.fronties.socialeventchat.helperClasses.Resource
import com.fronties.socialeventchat.helperClasses.file.FileHandler
import com.fronties.socialeventchat.helperClasses.file.FileType
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
    private val gson: Gson,
    private val sessionManager: SessionManager,
    private val fileHandler: FileHandler,
    private val profileInfoValidator: ProfileInfoValidator
) : ProfileRepo {

    override suspend fun saveUserProfile(
        firstName: String?,
        lastName: String?,
        phoneNumber: String?,
        profilePic: Uri?
    ) : Boolean {
        return if(
            profileInfoValidator.checkIfEntered(firstName) &&
            profileInfoValidator.checkIfEntered(lastName)
        ){
            profileDao.insertProfile(
                ProfileEntity(
                    sessionManager.fetchUid(),
                    firstName!!,
                    lastName!!,
                    phoneNumber,
                    profilePic
                )
            )
            true
        } else {
            false
        }

    }

    override fun loadAllProfile(): LiveData<List<ProfileEntity>> {
        return profileDao.loadAllProfile()
    }

    override fun loadById(): LiveData<ProfileEntity?>? {
        return profileDao.loadProfileById(sessionManager.fetchUid())
    }

    override fun createImageFile(imageUri: Uri?): File? {
        return fileHandler.createFile(
            imageUri,
            sessionManager.fetchUid().toString(),
            FileType.USER
        )
    }

    override suspend fun updateProfile(rb: File?, user: User): User? {
        try {
            var filePart: MultipartBody.Part? = null
            if (rb != null) {
                filePart = MultipartBody.Part.createFormData(
                    "file", rb.name,
                    rb
                        .asRequestBody("image/*".toMediaTypeOrNull()),
                )
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

    override fun getImageFile(): File? {
        return fileHandler.getFile(
            FileType.USER,
            sessionManager.fetchUid().toString()
        )
    }
}
