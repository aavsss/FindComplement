package com.fronties.socialeventchat.chat.dependency

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
object ChatModule {

    @Singleton
    @Provides
    fun provideRetroApiInstance(): ChatApi = Retrofit.Builder()
        .baseUrl("baseURL") // TODO put the url hosted
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ChatApi::class.java)
}
