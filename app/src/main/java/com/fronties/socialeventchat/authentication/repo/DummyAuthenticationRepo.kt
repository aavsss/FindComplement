package com.fronties.socialeventchat.authentication.repo

import com.auth0.android.jwt.JWT
import com.fronties.socialeventchat.authentication.api.AuthApi
import com.fronties.socialeventchat.authentication.dependency.SessionManager
import com.fronties.socialeventchat.helperClasses.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DummyAuthenticationRepo @Inject constructor(
    private val sessionManager: SessionManager
) : AuthenticationRepo {
    override suspend fun registerUser(username: String, password: String): Boolean {
        try {
            val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ"
            sessionManager.saveAuthToken(token)
            return true
        } catch (e: IOException) {
            Resource.error(e.localizedMessage ?: "IO Error", null)
            throw e
        } catch (e: HttpException) {
            Resource.error(e.localizedMessage ?: "HTTP Error", null)
            throw e
        }
    }

    override suspend fun loginUser(username: String, password: String): Boolean {
        try {
            val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ"
            sessionManager.saveAuthToken(token)
            return true
        } catch (e: IOException) {
            Resource.error(e.localizedMessage ?: "IO Error", null)
            throw e
        } catch (e: HttpException) {
            Resource.error(e.localizedMessage ?: "HTTP Error", null)
            throw e
        }
    }
}
