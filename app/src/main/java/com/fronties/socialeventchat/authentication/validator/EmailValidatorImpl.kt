package com.fronties.socialeventchat.authentication.validator

import javax.inject.Inject

class EmailValidatorImpl @Inject constructor() : EmailValidator {
    override fun validatePatriotsEmail(email: String): Boolean {
        val splitEmail = email.split('@')
        return splitEmail[1] == "patriots.uttyler.edu"
    }
}
