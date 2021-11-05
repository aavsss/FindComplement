package com.fronties.socialeventchat.profile.model

data class User (
    val uid: Int? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val email: String? = null,
    val phonenumber: String? = null,
    val imageurl: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)