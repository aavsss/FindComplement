package com.fronties.socialeventchat.authentication.validator

import java.lang.IndexOutOfBoundsException
import javax.inject.Inject

class EmailValidatorImpl @Inject constructor() : EmailValidator {
    override fun validatePatriotsEmail(email: String): Boolean {
        return try {
            val splitEmail = email.split('@')
            splitEmail[1] == "patriots.uttyler.edu"
        } catch (e: IndexOutOfBoundsException) {
            false
        }
    }
}
