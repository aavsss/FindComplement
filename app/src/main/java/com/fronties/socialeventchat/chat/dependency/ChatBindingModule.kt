package com.fronties.socialeventchat.chat.dependency

import com.fronties.socialeventchat.authentication.repo.AuthenticationRepo
import com.fronties.socialeventchat.authentication.repo.AuthenticationRepoImpl
import com.fronties.socialeventchat.chat.repo.ChatRepo
import com.fronties.socialeventchat.chat.repo.ChatRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ChatBindingModule {

    @Singleton
    @Binds
    abstract fun bindAuthenticationRepo(
        chatRepoImpl: ChatRepoImpl
    ): ChatRepo
}
