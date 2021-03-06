package com.fronties.socialeventchat.event.api

import com.fronties.socialeventchat.chat.model.MessageResponse
import com.fronties.socialeventchat.event.model.AttendEventRequestBody
import com.fronties.socialeventchat.event.model.EventResponse
import com.fronties.socialeventchat.event.model.SocialEvents
import com.fronties.socialeventchat.event.model.SortRequestBody
import retrofit2.Response
import retrofit2.http.*

interface EventApi {

    @GET("/api/events/")
    suspend fun getEventsList(): Response<List<SocialEvents>>

    @GET("/api/events/{eid}")
    suspend fun getEventDetails(
        @Path("eid") eid: Int
    ): Response<SocialEvents>

    @POST("/api/events/")
    suspend fun addEvent(
        @Body event: SocialEvents
    ): Response<EventResponse>

    @PUT("/api/events/")
    suspend fun editEvent(
        @Body event: SocialEvents
    ): Response<EventResponse>

    @DELETE()
    suspend fun deleteEvent(
        @Path("eid") eid: Int
    ): Response<EventResponse>

    @POST("/api/events/{eid}/join")
    suspend fun joinEvent(
        @Path("eid") eid: Int,
        @Body attendEventRequestBody: AttendEventRequestBody
    ): Response<EventResponse>

    // to get chats
    @GET("/api/events/{eid}/chats")
    suspend fun getChats(
        @Path("eid") eid: Int
    ): Response<MutableList<MessageResponse>>

    @GET("/api/events/attendingevents/{uid}")
    suspend fun getAttendedEvents(
        @Path("uid") uid: Int
    ): Response<List<SocialEvents>>

    @GET("/api/events/myevents/{uid}")
    suspend fun getMyEvents(
        @Path("uid") uid: Int
    ): Response<List<SocialEvents>>

    @POST("/api/events/new/sort")
    suspend fun sortUnattendedEvents(
        @Body sortRequestBody: SortRequestBody
    ): Response<List<SocialEvents>>

    @POST("/api/events/myevents/sort")
    suspend fun sortMyEvents(
        @Body sortRequestBody: SortRequestBody
    ): Response<List<SocialEvents>>

    @POST("/api/events/attendingevents/sort")
    suspend fun sortAttendingEvents(
        @Body sortRequestBody: SortRequestBody
    ): Response<List<SocialEvents>>
}
