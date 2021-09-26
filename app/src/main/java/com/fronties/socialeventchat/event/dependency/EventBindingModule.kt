package com.fronties.socialeventchat.event.dependency

import com.fronties.socialeventchat.event.repo.DummyRepoImpl
import com.fronties.socialeventchat.event.repo.EventRepo
import com.fronties.socialeventchat.event.repo.EventRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EventBindingModule {

    @Singleton
    @Binds
    abstract fun bindEventRepo(
        eventRepoImpl: DummyRepoImpl
    ): EventRepo
}
