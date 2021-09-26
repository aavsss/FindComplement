package com.fronties.socialeventchat.event.dependency

import com.fronties.socialeventchat.chat.api.ChatApi
import com.fronties.socialeventchat.event.api.EventApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventModule {

    @Singleton
    @Provides
    fun provideEventApiInstance(): EventApi = Retrofit.Builder()
        .baseUrl("baseURL") // TODO put the url hosted
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EventApi::class.java)
}