package com.fronties.socialeventchat.application.phoneValidator

import android.telephony.PhoneNumberUtils
import android.util.Patterns
import java.util.regex.Pattern
import javax.inject.Inject

class PhoneNumberValidatorImpl @Inject constructor() : PhoneNumberValidator {
    override fun validatePhoneNumber(number: String?) {
        if (number == null) {
            throw PhoneNumberException("Phone number is Empty")
        }
        val phoneNumberReg = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}\$"
        val pattern = Pattern.compile(phoneNumberReg)
        if (!pattern.matcher(number).find()) {
            throw PhoneNumberException("Invalid phone number")
        }
    }
}
