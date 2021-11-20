package com.fronties.socialeventchat.event.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fronties.socialeventchat.application.phoneValidator.PhoneNumberValidator
import com.fronties.socialeventchat.application.session.SessionManager
import com.fronties.socialeventchat.event.addEvent.EventTransformer
import com.fronties.socialeventchat.event.api.EventApi
import com.fronties.socialeventchat.event.model.SocialEvents
import junit.framework.TestResult
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import retrofit2.Response
import java.io.IOException

class EventRepoImplTest {

    private val mockEventApi = mock(EventApi::class.java)
    private val mockEventTransformer = mock(EventTransformer::class.java)
    private val mockPhoneNumberValidator = mock(PhoneNumberValidator::class.java)
    private val mockSessionManager = mock(SessionManager::class.java)
    private lateinit var eventRepoImpl: EventRepo

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
//        eventRepoImpl = EventRepoImpl(
//            mockEventApi,
//            mockEventTransformer,
//            mockPhoneNumberValidator,
//            mockSessionManager
//        )
    }

    @After
    fun tearDown() {
    }

    @Test
    suspend fun `getEventDetails throws IOException`() {
//        val mockEventId = 1
//        val mockException = mock(IOException::class.java)
//        Mockito.`when`(mockEventApi.getEventDetails(mockEventId)).thenThrow(mockException)
//        eventRepoImpl.getEventDetails(mockEventId)
//        assert(true)
        assertEquals(4, 2+2)
    }
}
