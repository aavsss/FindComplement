package com.fronties.socialeventchat.authentication.dependency

import com.fronties.socialeventchat.authentication.repo.AuthenticationRepo
import com.fronties.socialeventchat.authentication.repo.AuthenticationRepoImpl
import com.fronties.socialeventchat.authentication.validator.EmailValidator
import com.fronties.socialeventchat.authentication.validator.EmailValidatorImpl
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
        authenticationRepo: AuthenticationRepoImpl
    ): AuthenticationRepo

    @Singleton
    @Binds
    abstract fun bindEmailValidator(
        emailValidatorImpl: EmailValidatorImpl
    ): EmailValidator
}
