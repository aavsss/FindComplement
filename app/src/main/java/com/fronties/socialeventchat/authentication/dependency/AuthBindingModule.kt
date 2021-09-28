package com.fronties.socialeventchat.authentication.dependency

import com.fronties.socialeventchat.authentication.repo.AuthenticationRepo
import com.fronties.socialeventchat.authentication.repo.DummyAuthenticationRepo
import com.fronties.socialeventchat.event.repo.EventRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthBindingModule {

    @Singleton
    @Binds
    abstract fun bindAuthenticationRepo(
        authenticationRepo: DummyAuthenticationRepo
    ): AuthenticationRepo
}
