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

    override suspend fun addEvent(socialEvents: SocialEvents): Boolean {
        eventList.add(socialEvents)
        return true
    }

    override suspend fun attendEvent(eventId: Int): Boolean {
        TODO("Not yet implemented")
    }
}
