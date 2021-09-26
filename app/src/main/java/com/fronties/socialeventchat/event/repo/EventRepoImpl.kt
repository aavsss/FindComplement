package com.fronties.socialeventchat.event.repo

import com.fronties.socialeventchat.event.model.SocialEvents
import javax.inject.Inject

class EventRepoImpl @Inject constructor() : EventRepo {
    override fun getEventDetails(eventId: Int): SocialEvents {
        TODO("Not yet implemented")
    }
}