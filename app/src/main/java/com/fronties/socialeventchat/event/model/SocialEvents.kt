package com.fronties.socialeventchat.event.model

data class SocialEvents(
    val eid: Int? = 1,
    val name: String? = "",
    val description: String? = "Description",
    val type: String? = "Type",
    val contactNumber: Int? = 1,
    val startDate: String? = "StartDate",
    val endDate: String? = "EndDate",
    val host: String? = "Host",
    val location: String? = "Location"
)