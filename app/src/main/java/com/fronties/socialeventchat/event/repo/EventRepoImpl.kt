package com.fronties.socialeventchat.event.repo

import com.fronties.socialeventchat.event.api.EventApi
import com.fronties.socialeventchat.event.model.SocialEvents
import com.fronties.socialeventchat.helperClasses.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EventRepoImpl @Inject constructor(
    private val eventApi: EventApi
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

    override suspend fun addEvent(socialEvents: SocialEvents): Boolean {
        try {
            val eventResponse = eventApi.addEvent(socialEvents)
            if (eventResponse.isSuccessful && eventResponse.body() != null) {
                return true
            }
            return false
        } catch (e: IOException) {
            Resource.error(e.localizedMessage ?: "IO Error", null)
            throw e
        } catch (e: HttpException) {
            Resource.error(e.localizedMessage ?: "HTTP Error", null)
            throw e
        }
    }

    override suspend fun attendEvent(eventId: Int): Boolean {
        TODO("Not yet implemented")
    }
}