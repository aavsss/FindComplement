package com.fronties.socialeventchat.splashscreen.repo

import com.fronties.socialeventchat.application.session.sessionManager.SessionManager
import com.fronties.socialeventchat.splashscreen.dependency.jwt.JWTTransformer
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class SplashRepoImplTest {

    private val mockSessionManager = mock(SessionManager::class.java)
    private val mockJWTTransformer = mock(JWTTransformer::class.java)
    private lateinit var splashRepoImpl: SplashRepoImpl

    @Before
    fun setup() {
        splashRepoImpl = SplashRepoImpl(
            mockSessionManager,
            mockJWTTransformer
        )
    }

    @After
    fun teardown() {}

    @Test
    fun `isUserLoggedIn returns true`() {
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjUsImVtYWlsIjoicmFuZG9tQHBhdHJpb3RzLnV0dHlsZXIuZWR1IiwiaWF0IjoxNjM4NjY4NjQ0LCJleHAiOjE2NDM4NTI2NDR9.MsN7CSMcOSN22yQ_42cBZO6foicaCJu00-xwG_J35uM"
        `when`(mockSessionManager.fetchAuthToken()).thenReturn(token)
        `when`(mockJWTTransformer.isNotExpired()).thenReturn(true)
        val isLoggedIn = splashRepoImpl.isUserLoggedIn()
        assertThat(isLoggedIn)
    }

    @Test
    fun `isUserLoggedIn returns token expired, returns false`() {
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjUsImVtYWlsIjoicmFuZG9tQHBhdHJpb3RzLnV0dHlsZXIuZWR1IiwiaWF0IjoxNjM4NjY4NjQ0LCJleHAiOjE2NDM4NTI2NDR9.MsN7CSMcOSN22yQ_42cBZO6foicaCJu00-xwG_J35uM"
        `when`(mockSessionManager.fetchAuthToken()).thenReturn(token)
        `when`(mockJWTTransformer.isNotExpired()).thenReturn(false)
        val isLoggedIn = splashRepoImpl.isUserLoggedIn()
        assertThat(!isLoggedIn)
    }

    @Test
    fun `isUserLoggedIn invalid token, returns false`() {
        `when`(mockSessionManager.fetchAuthToken()).thenReturn(null)
        val isLoggedIn = splashRepoImpl.isUserLoggedIn()
        assertThat(!isLoggedIn)
    }
}
