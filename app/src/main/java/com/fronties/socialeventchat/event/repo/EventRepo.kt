package com.fronties.socialeventchat.event.repo

import com.fronties.socialeventchat.event.model.SocialEvents

interface EventRepo {
    fun getEventDetails(eventId: Int): SocialEvents
}