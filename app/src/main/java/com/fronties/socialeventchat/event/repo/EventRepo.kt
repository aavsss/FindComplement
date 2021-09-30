package com.fronties.socialeventchat.event.repo

import com.fronties.socialeventchat.event.model.SocialEvents

interface EventRepo {
    suspend fun getEventDetails(eventId: Int): SocialEvents

    // to generate dummy data for now, actually gets list of events later
    suspend fun getEventsList(): List<SocialEvents>
}