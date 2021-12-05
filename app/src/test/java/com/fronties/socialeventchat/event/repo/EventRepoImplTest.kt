package com.fronties.socialeventchat.event.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fronties.socialeventchat.MainCoroutineRule
import com.fronties.socialeventchat.application.phoneValidator.PhoneNumberValidator
import com.fronties.socialeventchat.application.session.sessionManager.SessionManager
import com.fronties.socialeventchat.event.addEvent.EventTransformer
import com.fronties.socialeventchat.event.api.EventApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
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
    private var eventRepoImpl: EventRepoImpl? = null

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
    fun `test event repo setup`() {
        assert(eventRepoImpl != null)
    }
}
