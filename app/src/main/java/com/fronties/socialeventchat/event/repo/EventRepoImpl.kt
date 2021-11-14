package com.fronties.socialeventchat.event.repo

import com.fronties.socialeventchat.application.phoneValidator.PhoneNumberValidator
import com.fronties.socialeventchat.application.session.AuthException
import com.fronties.socialeventchat.application.session.SessionManager
import com.fronties.socialeventchat.event.addEvent.EventTransformer
import com.fronties.socialeventchat.event.api.EventApi
import com.fronties.socialeventchat.event.dependency.sorting.SortOrder
import com.fronties.socialeventchat.event.dependency.sorting.SortType
import com.fronties.socialeventchat.event.model.AttendEventRequestBody
import com.fronties.socialeventchat.event.model.SocialEvents
import com.fronties.socialeventchat.event.model.SortRequestBody
import com.fronties.socialeventchat.helperClasses.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import kotlin.Exception

class EventRepoImpl @Inject constructor(
    private val eventApi: EventApi,
    private val eventTransformer: EventTransformer,
    private val phoneNumberValidator: PhoneNumberValidator,
    private val sessionManager: SessionManager
) : EventRepo {
    override suspend fun getEventDetails(eventId: Int): SocialEvents {
        try {
            val eventResponse = eventApi.getEventDetails(eventId)
            if (eventResponse.isSuccessful && eventResponse.body() != null) {
                return eventResponse.body()!!
            }
            return SocialEvents()
        } catch (e: IOException) {
            Resource.error(e.localizedMessage ?: "IO Error", null)
            throw e
        } catch (e: HttpException) {
            Resource.error(e.localizedMessage ?: "HTTP Error", null)
            throw e
        }
    }

    override suspend fun getEventsList(): List<SocialEvents> {
        try {
            val eventListResponse = eventApi.getEventsList()
            if (eventListResponse.isSuccessful && eventListResponse.body() != null) {
                return eventListResponse.body()!!
            }
            return emptyList()
        } catch (e: IOException) {
            throw e
        } catch (e: HttpException) {
            throw e
        }
    }

    override suspend fun addEvent(
        name: String?,
        description: String?,
        eventType: String?,
        contactNumber: String?,
        startDate: Triple<Int, Int, Int>?,
        startTime: Pair<Int, Int>?,
        endDate: Triple<Int, Int, Int>?,
        endTime: Pair<Int, Int>?,
        hostName: String?
    ): Boolean {
        try {
            val socialEvents = SocialEvents(
                name = name,
                description = description,
                eventtype = eventType,
                contactnumber = contactNumber,
                starttime = eventTransformer.transformDateToUTC(startDate, startTime),
                endtime = eventTransformer.transformDateToUTC(endDate, endTime),
                hostname = hostName
            )
            eventTransformer.checkRequiredItems(socialEvents)
            phoneNumberValidator.validatePhoneNumber(contactNumber)
            val eventResponse = eventApi.addEvent(socialEvents)
            if (eventResponse.isSuccessful && eventResponse.body() != null) {
                return true
            }
            return false
        } catch (e: Exception) {
            Resource.error(e.localizedMessage ?: "Unknown error", null)
            throw e
        }
    }

    override suspend fun getGoingEvents(): List<SocialEvents> {
        try {
            val eventListResponse = eventApi.getAttendedEvents(sessionManager.fetchUid())
            if (eventListResponse.isSuccessful && eventListResponse.body() != null) {
                return eventListResponse.body()!!
            }
            return emptyList()
        } catch (e: AuthException) {
            Resource.error(e.localizedMessage ?: "Auth Error", null)
            throw e
        } catch (e: Exception) {
            Resource.error(e.localizedMessage ?: "Error", null)
            throw e
        }
    }

    override suspend fun getMyEvents(): List<SocialEvents> {
        try {
            val myEventListResponse = eventApi.getMyEvents(sessionManager.fetchUid())
            if (myEventListResponse.isSuccessful && myEventListResponse.body() != null) {
                return myEventListResponse.body()!!
            }
            return emptyList()
        } catch (e: AuthException) {
            Resource.error(e.localizedMessage ?: "Auth Error", null)
            throw e
        } catch (e: Exception) {
            Resource.error(e.localizedMessage ?: "Error", null)
            throw e
        }
    }

    override suspend fun attendEvent(eventId: Int): Boolean {
        try {
            val attendEventR =
                eventApi.joinEvent(
                    eventId,
                    AttendEventRequestBody(
                        sessionManager.fetchUid(),
                        eventId
                    )
                )
            if (attendEventR.isSuccessful) {
                return true
            }
            return false
        } catch (e: Exception) {
            Resource.error(e.localizedMessage ?: "Unknown error occurred", null)
            return false
        }
    }

    override suspend fun sortEvents(sortType: SortType, sortOrder: SortOrder): List<SocialEvents> {
        try {
            val sortEventResponse = eventApi.sortEvents(
                SortRequestBody(sortType, sortOrder)
            )
            if (sortEventResponse.isSuccessful && sortEventResponse.body() != null) {
                return sortEventResponse.body()!!
            }
        } catch (e: Exception) {
            Resource.error(e.localizedMessage ?: "Unknown error occurred", null)
        }
        return emptyList()
    }
}
