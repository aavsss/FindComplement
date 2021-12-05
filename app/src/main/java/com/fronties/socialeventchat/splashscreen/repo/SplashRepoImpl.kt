package com.fronties.socialeventchat.splashscreen.repo

import com.fronties.socialeventchat.application.session.sessionManager.SessionManager
import com.fronties.socialeventchat.splashscreen.dependency.jwt.JWTTransformer
import javax.inject.Inject

class SplashRepoImpl @Inject constructor(
    private val sessionManager: SessionManager,
    private val jwtTransformer: JWTTransformer
) : SplashRepo {
    override fun isUserLoggedIn(): Boolean {
        val authToken = sessionManager.fetchAuthToken()
        authToken?.let { token ->
            jwtTransformer.initializeToken(token)
            if (jwtTransformer.isNotExpired()) {
                return true
            }
            return false
        }
        return false
    }
}
