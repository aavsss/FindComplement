package com.fronties.socialeventchat.event.model

data class SocialEvents(
    val eid: Int,
    val description: String?,
    val type: String?,
    val contactNumber: Int?,
    val startDate: String?,
    val endDate: String?,
    val host: String?,
    val location: String?
)