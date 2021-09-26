package com.fronties.socialeventchat.event.repo

import com.fronties.socialeventchat.event.model.SocialEvents
import javax.inject.Inject

class DummyRepoImpl @Inject constructor() : EventRepo {
    override fun getEventDetails(eventId: Int): SocialEvents {
        return SocialEvents(
            eid = eventId,
        )
    }
}
