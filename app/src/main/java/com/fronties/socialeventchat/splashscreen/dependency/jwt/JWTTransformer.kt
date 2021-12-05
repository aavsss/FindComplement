package com.fronties.socialeventchat.splashscreen.dependency.jwt

import com.auth0.android.jwt.JWT

interface JWTTransformer {
    var jwt: JWT?
    fun initializeToken(token: String)
    fun isNotExpired(): Boolean
}
