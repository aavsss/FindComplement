package com.fronties.socialeventchat.splashscreen.repo

import com.auth0.android.jwt.JWT
import com.fronties.socialeventchat.application.session.sessionManager.SessionManagerImpl
import javax.inject.Inject

class SplashRepoImpl @Inject constructor(
    private val sessionManagerImpl: SessionManagerImpl
) : SplashRepo {
    override fun isUserLoggedIn(): Boolean {
        val authToken = sessionManagerImpl.fetchAuthToken()
        authToken?.let { token ->
            val jwt = JWT(token)
            if (jwt.isNotExpired()) {
                return true
            }
            return false
        }
        return false
    }

    fun JWT.isNotExpired(): Boolean {
        return !this.isExpired(0L)
    }
}
