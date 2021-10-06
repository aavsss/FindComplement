package com.fronties.socialeventchat.application.session

import okhttp3.OkHttpClient

class OkHttpClientWithInterceptor {

    fun getOkHttpClientWithInterceptor(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }
}