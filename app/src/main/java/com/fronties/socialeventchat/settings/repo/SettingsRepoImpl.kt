package com.fronties.socialeventchat.settings.repo

import com.fronties.socialeventchat.application.session.SessionManager
import javax.inject.Inject

class SettingsRepoImpl @Inject constructor(
    private val sessionManager: SessionManager
) : SettingsRepo {
    override suspend fun logout(): Boolean {
        sessionManager.removeAuthToken()
        sessionManager.removeUid()
        return true // maybe make this more informative
    }

}