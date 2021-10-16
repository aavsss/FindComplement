package com.fronties.socialeventchat.event.repo

import com.fronties.socialeventchat.event.model.SocialEvents
import javax.inject.Inject

class DummyRepoImpl @Inject constructor() : EventRepo {
    override suspend fun getEventDetails(eventId: Int): SocialEvents {
        return SocialEvents(
            eid = eventId,
        )
    }

    override suspend fun getEventsList(): List<SocialEvents> {
        // generate list of dummy events
        return listOf(
            SocialEvents(eventName = "First"),
            SocialEvents(eventName = "Second")
        )
    }

    override suspend fun addEvent(socialEvents: SocialEvents) {
        println("Added!")
    }
}
