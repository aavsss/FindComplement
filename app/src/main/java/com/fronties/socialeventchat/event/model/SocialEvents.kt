package com.fronties.socialeventchat.event.model

data class SocialEvents(
    val eid: Int? = 1,
    val eventName: String? = "",
    val eventDescription: String? = "Description",
    val eventType: String? = "Type",
    val eventContactNumber: Int? = 1,
    val eventStartDate: String? = "StartDate",
    val eventEndDate: String? = "EndDate",
    val eventHost: String? = "Host",
    val location: String? = "Location"
)