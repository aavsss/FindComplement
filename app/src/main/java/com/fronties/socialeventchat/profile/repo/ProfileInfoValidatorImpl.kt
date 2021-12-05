package com.fronties.socialeventchat.profile.repo

import android.text.TextUtils
import javax.inject.Inject

class ProfileInfoValidatorImpl @Inject constructor() : ProfileInfoValidator {
    override fun checkIfEntered(enteredInfo: String?): Boolean {
        if (enteredInfo == null) {
            return false
        }
        when {
            TextUtils.isEmpty(enteredInfo?.trim { it <= ' ' }) -> {
                return false
            }
        }
        return true
    }
}
