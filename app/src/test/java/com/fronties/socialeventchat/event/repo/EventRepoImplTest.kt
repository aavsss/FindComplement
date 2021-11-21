package com.fronties.socialeventchat.event.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fronties.socialeventchat.MainCoroutineRule
import com.fronties.socialeventchat.application.phoneValidator.PhoneNumberValidator
import com.fronties.socialeventchat.application.session.sessionManager.SessionManager
import com.fronties.socialeventchat.event.addEvent.EventTransformer
import com.fronties.socialeventchat.event.api.EventApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.*
import retrofit2.Response

@ExperimentalCoroutinesApi
class EventRepoImplTest {

    private val mockEventApi = mock(EventApi::class.java)
    private val mockEventTransformer = mock(EventTransformer::class.java)
    private val mockPhoneNumberValidator = mock(PhoneNumberValidator::class.java)
    private val mockSessionManager = mock(SessionManager::class.java)
    lateinit var eventRepoImpl: EventRepoImpl

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        eventRepoImpl = EventRepoImpl(
            mockEventApi,
            mockEventTransformer,
            mockPhoneNumberValidator,
            mockSessionManager
        )
    }

    @After
    fun teardown() {
    }

    @Test
    fun `sample test`() {
        assertEquals(4, 2 + 2)
    }

//    @Test
//    fun `get event details successfully gets response`() = runBlocking {
//        val mockEventId = 99
//        `when`(mockEventApi.getEventDetails(eq(mockEventId))).thenReturn(Response.success(SocialEvents()))
//        eventRepoImpl.getEventDetails(mockEventId)
//        assertEquals(4, 2 + 2)
//    }

    @Test
    fun `get event list successfully gets response`() = runBlocking {
        `when`(mockEventApi.getEventsList()).thenReturn(Response.success(emptyList()))
        val temp = eventRepoImpl.getEventsList()
    }
}
