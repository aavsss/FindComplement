package com.fronties.socialeventchat.application.phoneValidator

import android.telephony.PhoneNumberUtils
import javax.inject.Inject

class PhoneNumberValidatorImpl @Inject constructor() : PhoneNumberValidator {
    override fun validatePhoneNumber(number: String?): Boolean {
        return if (number != null) {
            PhoneNumberUtils.isGlobalPhoneNumber(number)
        } else {
            throw PhoneNumberException("Invalid phone number")
        }
    }
}
