package com.fronties.socialeventchat.authentication.dependency

import com.fronties.socialeventchat.authentication.api.AuthApi
import com.fronties.socialeventchat.chat.api.ChatApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideAuthApiInstance(): AuthApi = Retrofit.Builder()
        .baseUrl("baseURL") // TODO put the url hosted
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)
}