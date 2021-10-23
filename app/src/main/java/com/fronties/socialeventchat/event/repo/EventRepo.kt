package com.fronties.socialeventchat.event.repo

import com.fronties.socialeventchat.event.model.SocialEvents

interface EventRepo {
    suspend fun getEventDetails(eventId: Int): SocialEvents?

    // to generate dummy data for now, actually gets list of events later
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

    suspend fun attendEvent(eventId: Int): Boolean
}
