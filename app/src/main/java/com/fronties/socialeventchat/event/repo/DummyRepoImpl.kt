package com.fronties.socialeventchat.event.repo

import com.fronties.socialeventchat.event.model.SocialEvents
import javax.inject.Inject

class DummyRepoImpl @Inject constructor() : EventRepo {

    val eventList = mutableListOf(
        SocialEvents(eid = 0, name = "First"),
        SocialEvents(eid = 1, name = "Second")
    )

    override suspend fun getEventDetails(eventId: Int): SocialEvents? {
        return eventList.find {
            it.eid == eventId
        }
    }

    override suspend fun getEventsList(): List<SocialEvents> {
        // generate list of dummy events
        return eventList
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
        TODO("Not yet implemented")
    }


    override suspend fun attendEvent(eventId: Int): Boolean {
        TODO("Not yet implemented")
    }
}
