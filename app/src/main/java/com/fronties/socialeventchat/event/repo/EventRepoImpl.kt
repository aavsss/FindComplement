package com.fronties.socialeventchat.event.repo

import com.fronties.socialeventchat.application.phoneValidator.PhoneNumberException
import com.fronties.socialeventchat.application.phoneValidator.PhoneNumberValidator
import com.fronties.socialeventchat.application.session.AuthException
import com.fronties.socialeventchat.event.addEvent.EventTransformer
import com.fronties.socialeventchat.event.addEvent.MissingInfoException
import com.fronties.socialeventchat.event.api.EventApi
import com.fronties.socialeventchat.event.model.SocialEvents
import com.fronties.socialeventchat.helperClasses.Resource
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class EventRepoImpl @Inject constructor(
    private val eventApi: EventApi,
    private val eventTransformer: EventTransformer,
    private val phoneNumberValidator: PhoneNumberValidator
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
            Resource.error(e.localizedMessage ?: "IO Error", null)
            throw e
        } catch (e: HttpException) {
            Resource.error(e.localizedMessage ?: "HTTP Error", null)
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
        throw AuthException("Auth ")
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

    override suspend fun attendEvent(eventId: Int): Boolean {
        TODO("Not yet implemented")
    }
}
