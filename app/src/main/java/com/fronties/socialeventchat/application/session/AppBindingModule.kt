package com.fronties.socialeventchat.application.session

import com.fronties.socialeventchat.application.phoneValidator.PhoneNumberValidator
import com.fronties.socialeventchat.application.phoneValidator.PhoneNumberValidatorImpl
import com.fronties.socialeventchat.event.repo.EventRepo
import com.fronties.socialeventchat.event.repo.EventRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindingModule {
    @Singleton
    @Binds
    abstract fun bindPhoneValidator(
        phoneNumberValidatorImpl: PhoneNumberValidatorImpl
    ): PhoneNumberValidator
}
