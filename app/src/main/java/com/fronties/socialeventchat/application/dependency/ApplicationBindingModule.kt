package com.fronties.socialeventchat.application.dependency

import com.fronties.socialeventchat.helperClasses.dateTime.DateTimeUtils
import com.fronties.socialeventchat.helperClasses.dateTime.DateTimeUtilsImpl
import com.fronties.socialeventchat.helperClasses.file.FileHandler
import com.fronties.socialeventchat.helperClasses.file.FileHandlerImpl
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

    @Singleton
    @Binds
    abstract fun bindFileHandler(
        fileHandlerImpl: FileHandlerImpl
    ): FileHandler
}
