package com.fronties.socialeventchat.settings.repo

interface SettingsRepo {

    suspend fun logout() : Boolean
}