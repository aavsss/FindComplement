package com.fronties.socialeventchat.chat.dependency

import com.fronties.socialeventchat.chat.api.ChatApi
import com.fronties.socialeventchat.helperClasses.Constants.BASE_URL
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
    fun provideChatApiInstance(): ChatApi = Retrofit.Builder()
        .baseUrl(BASE_URL) // TODO put the url hosted
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ChatApi::class.java)
}
