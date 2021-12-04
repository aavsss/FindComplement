package com.fronties.socialeventchat.application.session.sessionManager

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManagerImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SessionManager {

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_ID = "user_id"
        const val USER_NAME = "user_name"
    }

    override fun saveAuthToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    override fun fetchAuthToken(): String? {
        return sharedPreferences.getString(USER_TOKEN, null)
    }

    override fun saveUid(uid: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(USER_ID, uid)
        editor.apply()
    }

    override fun fetchUid(): Int {
        return sharedPreferences.getInt(USER_ID, -1)
    }

    override fun saveUName(name: String) {
        val editor = sharedPreferences.edit()
        editor.putString(USER_NAME, name)
        editor.apply()
    }

    override fun fetchUName(): String {
        return sharedPreferences.getString(USER_NAME, "Unknown")!!
    }

    override fun removeAuthToken() {
        val editor = sharedPreferences.edit()
        editor.remove(USER_TOKEN)
        editor.apply()
    }

    override fun removeUid() {
        val editor = sharedPreferences.edit()
        editor.remove(USER_ID)
        editor.apply()
    }
}
