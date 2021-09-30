package com.fronties.socialeventchat.authentication.dependency

import android.content.Context
import com.fronties.socialeventchat.authentication.api.AuthApi
import com.fronties.socialeventchat.chat.api.ChatApi
import com.fronties.socialeventchat.helperClasses.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideAuthApiInstance(): AuthApi = Retrofit.Builder()
        .baseUrl(BASE_URL) // TODO put the url hosted
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClientWithInterceptor())
        .build()
        .create(AuthApi::class.java)

    private fun getOkHttpClientWithInterceptor(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }
}