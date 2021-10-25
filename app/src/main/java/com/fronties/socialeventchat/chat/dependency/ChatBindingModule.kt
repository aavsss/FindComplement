package com.fronties.socialeventchat.chat.dependency

import com.fronties.socialeventchat.chat.repo.ChatRepo
import com.fronties.socialeventchat.chat.repo.ChatRepoIOImpl
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
        chatRepoImpl: ChatRepoIOImpl
    ): ChatRepo
}
