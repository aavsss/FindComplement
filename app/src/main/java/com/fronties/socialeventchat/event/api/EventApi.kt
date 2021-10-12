package com.fronties.socialeventchat.event.api

import com.fronties.socialeventchat.event.model.EventResponse
import com.fronties.socialeventchat.event.model.SocialEvents
import retrofit2.Response
import retrofit2.http.*

interface EventApi {

    @GET()
    suspend fun getEventsList(): Response<List<SocialEvents>>

    @GET()
    suspend fun getEventDetails(
        @Path("eid") eid: Int
    ): Response<SocialEvents>

    @POST()
    suspend fun addEvent(
        @Body event: SocialEvents
    ): Response<EventResponse>

    @PUT()
    suspend fun editEvent(
        @Body event: SocialEvents
    ): Response<EventResponse>

    @DELETE()
    suspend fun deleteEvent(
        @Path("eid") eid: Int
    ): Response<EventResponse>
}
