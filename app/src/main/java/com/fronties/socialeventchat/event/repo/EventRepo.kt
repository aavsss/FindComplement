package com.fronties.socialeventchat.event.repo

import com.fronties.socialeventchat.event.model.SocialEvents

interface EventRepo {
    fun getEventDetails(eventId: Int): SocialEvents

    // to generate dummy data for now, actually gets list of events later
    fun getEventsList(): List<SocialEvents>
}