package com.fronties.socialeventchat.settings.repo

import com.fronties.socialeventchat.application.session.sessionManager.SessionManager
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class SettingsRepoImplTest {

    private val mockSessionManager = mock(SessionManager::class.java)
    private var settingsRepoImpl: SettingsRepoImpl? = null

    @Before
    fun setup() {
        settingsRepoImpl = SettingsRepoImpl(
            mockSessionManager
        )
    }

    @After
    fun teardown() {
        settingsRepoImpl = null
    }

    @Test
    fun `initial setup`() {
        assert(settingsRepoImpl != null)
    }

    @Test
    fun `logOut removed auth token called`() = runBlockingTest {
        settingsRepoImpl?.logout()
        verify(mockSessionManager, times(1)).removeAuthToken()
    }

    @Test
    fun `logOut removed Uid called`() = runBlockingTest {
        settingsRepoImpl?.logout()
        verify(mockSessionManager, times(1)).removeUid()
    }

    @Test
    fun `logOut returns true after success`() = runBlockingTest {
        val status = settingsRepoImpl?.logout()
        assertThat(status)
    }
}
