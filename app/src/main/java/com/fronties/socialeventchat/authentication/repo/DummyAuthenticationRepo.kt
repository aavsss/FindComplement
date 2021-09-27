package com.fronties.socialeventchat.authentication.repo

import com.fronties.socialeventchat.authentication.dependency.SessionManager
import javax.inject.Inject

class DummyAuthenticationRepo @Inject constructor(
    private val sessionManager: SessionManager
) : AuthenticationRepo {
    override fun registerUser(username: String, password: String) {
        sessionManager.saveAuthToken("Bearer 123")
    }

    override fun loginUser(username: String, password: String) {
        sessionManager.saveAuthToken("Bearer 123")
    }
}