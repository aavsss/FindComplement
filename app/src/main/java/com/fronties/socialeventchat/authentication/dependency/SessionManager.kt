package com.fronties.socialeventchat.authentication.dependency

import android.content.SharedPreferences
import javax.inject.Inject

class SessionManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
}