package com.fronties.socialeventchat.settings.repo

import com.fronties.socialeventchat.application.session.sessionManager.SessionManagerImpl
import javax.inject.Inject

class SettingsRepoImpl @Inject constructor(
    private val sessionManagerImpl: SessionManagerImpl
) : SettingsRepo {
    override suspend fun logout(): Boolean {
        sessionManagerImpl.removeAuthToken()
        sessionManagerImpl.removeUid()
        return true // maybe make this more informative
    }

}