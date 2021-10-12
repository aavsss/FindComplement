package com.fronties.socialeventchat.profile.dependency

import com.fronties.socialeventchat.application.session.AuthInterceptor
import com.fronties.socialeventchat.helperClasses.Constants.BASE_URL
import com.fronties.socialeventchat.profile.api.ProfileApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Singleton
    @Provides
    fun provideProfileApiInstance(
        authInterceptor: AuthInterceptor
    ): ProfileApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(authInterceptor.getOkHttpClientWithInterceptor())
        .build()
        .create(ProfileApi::class.java)
}
