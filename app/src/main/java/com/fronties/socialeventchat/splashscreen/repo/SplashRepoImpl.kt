package com.fronties.socialeventchat.splashscreen.repo

import com.auth0.android.jwt.JWT
import com.fronties.socialeventchat.application.session.SessionManager
import javax.inject.Inject

class SplashRepoImpl @Inject constructor(
    private val sessionManager: SessionManager
) : SplashRepo {
    override fun isUserLoggedIn(): Boolean {
        val authToken = sessionManager.fetchAuthToken()
        authToken?.let { token ->
            val jwt = JWT(token)
            if (!jwt.isExpired(0L)) {
                return true
            }
            return false
        }
        sessionManager.removeAuthToken()
        return false
    }
}
