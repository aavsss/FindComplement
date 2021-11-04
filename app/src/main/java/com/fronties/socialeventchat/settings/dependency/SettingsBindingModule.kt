package com.fronties.socialeventchat.settings.dependency

import com.fronties.socialeventchat.settings.repo.SettingsRepo
import com.fronties.socialeventchat.settings.repo.SettingsRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsBindingModule {

    @Singleton
    @Binds
    abstract fun bindSettingsRepo (
        settingsRepoImpl: SettingsRepoImpl
    ): SettingsRepo
}