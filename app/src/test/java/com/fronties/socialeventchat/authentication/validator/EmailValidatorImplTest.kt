package com.fronties.socialeventchat.authentication.validator

import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class EmailValidatorImplTest {

    private lateinit var emailValidatorImpl: EmailValidatorImpl

    @Before
    fun setup() {
        emailValidatorImpl = EmailValidatorImpl()
    }

    @After
    fun teardown() {}

    @Test
    fun `validatePatriotsEmail with patriots email, returns true`() {
        val email = "test@patriots.uttyler.edu"
        assertThat(emailValidatorImpl.validatePatriotsEmail(email))
    }

    @Test
    fun `validatePatriotsEmail with other alias, returns false`() {
        val email = "test@gmail.com"
        assertThat(!emailValidatorImpl.validatePatriotsEmail(email))
    }

    @Test
    fun `invalid email throws indexOutOfBoundsException`() {
        val email = "test@test@test"
        assertThat(emailValidatorImpl.validatePatriotsEmail(email))
    }
}
