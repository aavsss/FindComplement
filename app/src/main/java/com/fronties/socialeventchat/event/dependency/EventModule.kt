package com.fronties.socialeventchat.event.dependency

import com.fronties.socialeventchat.application.session.AuthInterceptor
import com.fronties.socialeventchat.chat.api.ChatApi
import com.fronties.socialeventchat.event.api.EventApi
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
object EventModule {

    @Singleton
    @Provides
    fun provideEventApiInstance(
        authInterceptor: AuthInterceptor
    ): EventApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(authInterceptor.getOkHttpClientWithInterceptor())
        .build()
        .create(EventApi::class.java)
}
