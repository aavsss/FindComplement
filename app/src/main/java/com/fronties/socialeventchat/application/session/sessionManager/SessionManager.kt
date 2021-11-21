package com.fronties.socialeventchat.application.session.sessionManager

interface SessionManager {
    fun saveAuthToken(token: String)
    fun fetchAuthToken(): String?
    fun saveUid(uid: Int)
    fun fetchUid(): Int
    fun saveUName(name: String)
    fun fetchUName(): String
    fun removeAuthToken()
    fun removeUid()
}
