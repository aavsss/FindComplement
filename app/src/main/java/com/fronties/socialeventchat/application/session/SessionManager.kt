package com.fronties.socialeventchat.application.session

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_ID = "user_id"
    }

    fun saveAuthToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return sharedPreferences.getString(USER_TOKEN, null)
    }

    fun saveUid(uid: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(USER_ID, uid)
        editor.apply()
    }

    fun fetchUid(): Int {
        return sharedPreferences.getInt(USER_ID, -1)
    }

    fun removeAuthToken() {
        val editor = sharedPreferences.edit()
        editor.remove(USER_TOKEN)
        editor.apply()
    }
}
