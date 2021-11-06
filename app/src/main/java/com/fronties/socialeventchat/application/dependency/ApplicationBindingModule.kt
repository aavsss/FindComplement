package com.fronties.socialeventchat.application.dependency

import com.fronties.socialeventchat.helperClasses.dateTime.DateTimeUtils
import com.fronties.socialeventchat.helperClasses.dateTime.DateTimeUtilsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationBindingModule {

    @Singleton
    @Binds
    abstract fun bindDateTimeUtils(
        dateTimeUtilsImpl: DateTimeUtilsImpl
    ): DateTimeUtils
}
