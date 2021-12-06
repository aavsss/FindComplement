package com.fronties.socialeventchat.splashscreen.dependency.jwt

import com.auth0.android.jwt.JWT
import javax.inject.Inject

class JWTTransformerImpl @Inject constructor() : JWTTransformer {

    override var jwt: JWT? = null

    override fun initializeToken(token: String) {
        jwt = JWT(token)
    }

    override fun isNotExpired(): Boolean {
        if (jwt != null) {
            return !jwt!!.isExpired(0L)
        }
        return false
    }

    override fun getUid(): Int {
        return jwt!!.getClaim("uid").asInt()!!
    }
}
