package com.fronties.socialeventchat.profile.repo

interface ProfileInfoValidator {
    fun checkIfEntered(enteredInfo: String?): Boolean
}