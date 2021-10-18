
package com.fronties.socialeventchat.profile.dependency

import com.fronties.socialeventchat.authentication.repo.AuthenticationRepo
import com.fronties.socialeventchat.authentication.repo.AuthenticationRepoImpl
import com.fronties.socialeventchat.authentication.validator.EmailValidator
import com.fronties.socialeventchat.authentication.validator.EmailValidatorImpl
import com.fronties.socialeventchat.profile.repo.ProfileRepo
import com.fronties.socialeventchat.profile.repo.ProfileRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileBindingModule {

    @Singleton
    @Binds
    abstract fun bindProfileRepo(
        profileRepoImpl: ProfileRepoImpl
    ): ProfileRepo
}