package com.fronties.socialeventchat.chat.dependency

import com.fronties.socialeventchat.application.session.AuthInterceptor
import com.fronties.socialeventchat.chat.api.ChatApi
import com.fronties.socialeventchat.helperClasses.Constants.BASE_URL
import com.fronties.socialeventchat.helperClasses.Constants.WS_RUL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    @Singleton
    @Provides
    fun provideChatApiInstance(
        authInterceptor: AuthInterceptor
    ): ChatApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(authInterceptor.getOkHttpClientWithInterceptor())
        .build()
        .create(ChatApi::class.java)

    @Singleton
    @Provides
    fun provideSocketOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .build()

    @Singleton
    @Provides
    fun provideWSRequestBuilder(): Request = Request
        .Builder()
        .url(WS_RUL)
        .build()
}
