package com.fronties.socialeventchat.application.session

import com.fronties.socialeventchat.application.session.sessionManager.SessionManager
import com.fronties.socialeventchat.application.session.sessionManager.SessionManagerImpl
import okhttp3.*
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    @Inject
    lateinit var sessionManager: SessionManager
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // if token has been saved, add it to the request
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }

    fun getOkHttpClientWithInterceptor(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(this)
            .authenticator(getOkHttpAuthenticator())
            .build()
    }

    private fun getOkHttpAuthenticator(): Authenticator {
        return object : Authenticator {
            override fun authenticate(route: Route?, response: Response): Request? {
                if (response.code == 401) {
                    throw AuthException("Auth Error")
                } else {
                    return response.request
                }
            }
        }
    }
}
