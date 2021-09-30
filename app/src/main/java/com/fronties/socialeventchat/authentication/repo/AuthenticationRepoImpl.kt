package com.fronties.socialeventchat.authentication.repo

import com.fronties.socialeventchat.authentication.api.AuthApi
import com.fronties.socialeventchat.authentication.dependency.SessionManager
import com.fronties.socialeventchat.helperClasses.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthenticationRepoImpl @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : AuthenticationRepo {
    override fun registerUser(username: String, password: String): Boolean {
        try {
            val authResponse = authApi.registerUser()
            if (authResponse.isSuccessful && authResponse.body() != null) {
                val token = authResponse.body()!!.token!!
                sessionManager.saveAuthToken(token)
                return true
            }
            return false
        } catch (e: IOException) {
            Resource.error(e.localizedMessage ?: "IO Error", null)
            throw e
        } catch (e: HttpException) {
            Resource.error(e.localizedMessage ?: "HTTP Error", null)
            throw e
        }
    }

    override fun loginUser(username: String, password: String): Boolean {
        return true
    }
}