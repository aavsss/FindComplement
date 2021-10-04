package com.fronties.socialeventchat.authentication.dependency

import com.fronties.socialeventchat.authentication.api.AuthApi
import com.fronties.socialeventchat.helperClasses.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideAuthApiInstance(
        authInterceptor: AuthInterceptor
    ): AuthApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClientWithInterceptor(authInterceptor))
        .build()
        .create(AuthApi::class.java)

    private fun getOkHttpClientWithInterceptor(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }
}
