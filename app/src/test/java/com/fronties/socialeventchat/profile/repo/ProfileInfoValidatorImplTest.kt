package com.fronties.socialeventchat.profile.repo

import org.junit.After
import org.junit.Before
import org.junit.Test

class ProfileInfoValidatorImplTest {

    private var profileInfoValidatorImpl: ProfileInfoValidatorImpl? = null

    @Before
    fun setup() {
        profileInfoValidatorImpl = ProfileInfoValidatorImpl()
    }

    @After
    fun teardown() {
        profileInfoValidatorImpl = null
    }

    @Test
    fun `checkIfEntered, entered null data, returns false`() {
        val enteredInfo = null
        val status = profileInfoValidatorImpl?.checkIfEntered(enteredInfo)!!
        assert(!status)
    }
}
