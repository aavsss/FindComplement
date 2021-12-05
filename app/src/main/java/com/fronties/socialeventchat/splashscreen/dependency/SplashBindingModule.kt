package com.fronties.socialeventchat.splashscreen.dependency

import com.fronties.socialeventchat.splashscreen.dependency.jwt.JWTTransformer
import com.fronties.socialeventchat.splashscreen.dependency.jwt.JWTTransformerImpl
import com.fronties.socialeventchat.splashscreen.repo.SplashRepo
import com.fronties.socialeventchat.splashscreen.repo.SplashRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SplashBindingModule {

    @Singleton
    @Binds
    abstract fun bindSplashRepo(
        splashRepo: SplashRepoImpl
    ): SplashRepo

    @Singleton
    @Binds
    abstract fun bindJWTTransformer(
        jwtTransformerImpl: JWTTransformerImpl
    ): JWTTransformer
}
