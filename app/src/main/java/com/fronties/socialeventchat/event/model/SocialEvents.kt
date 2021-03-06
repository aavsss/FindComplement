package com.fronties.socialeventchat.event.model

data class SocialEvents(
    val eid: Int? = 1,
    val name: String? = "",
    val description: String? = "Description",
    val eventtype: String? = "Type",
    val contactnumber: String? = "number",
    val starttime: String? = "StartDate",
    val endtime: String? = "EndDate",
    val hostname: String? = "Host",
    val location: String? = "Location",
    val imageurl: String? = "imageurl",
    val uid: Int? = 1,
    val participants: Int? = 0
)
