package com.fronties.socialeventchat.event.repo

import com.fronties.socialeventchat.event.dependency.sorting.SortOrder
import com.fronties.socialeventchat.event.dependency.sorting.SortType
import com.fronties.socialeventchat.event.model.SocialEvents
import com.fronties.socialeventchat.helperClasses.Resource

interface EventRepo {
    suspend fun getEventDetails(eventId: Int): SocialEvents?
    suspend fun getEventsList(): List<SocialEvents>
    suspend fun addEvent(
        name: String?,
        description: String?,
        eventType: String?,
        contactNumber: String?,
        startDate: Triple<Int, Int, Int>?,
        startTime: Pair<Int, Int>?,
        endDate: Triple<Int, Int, Int>?,
        endTime: Pair<Int, Int>?,
        hostName: String?
    ): Boolean
    suspend fun updateEvent (
        eventId: Int,
        name: String?,
        description: String?,
        eventType: String?,
        contactNumber: String?,
        startDate: Triple<Int, Int, Int>?,
        startTime: Pair<Int, Int>?,
        endDate: Triple<Int, Int, Int>?,
        endTime: Pair<Int, Int>?,
        hostName: String?
    ): Boolean
    suspend fun attendEvent(eventId: Int): Boolean
    suspend fun getGoingEvents(): List<SocialEvents>
    suspend fun getMyEvents(): List<SocialEvents>
    suspend fun sortUnattendedEvents(sortType: SortType, sortOrder: SortOrder): List<SocialEvents>
    suspend fun sortMyEvents(sortType: SortType, sortOrder: SortOrder): List<SocialEvents>
    suspend fun sortAttendedEvents(sortType: SortType, sortOrder: SortOrder): List<SocialEvents>
}
