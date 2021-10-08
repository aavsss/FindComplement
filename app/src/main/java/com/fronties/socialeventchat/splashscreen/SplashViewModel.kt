package com.fronties.socialeventchat.splashscreen

import androidx.lifecycle.ViewModel
import com.fronties.socialeventchat.splashscreen.repo.SplashRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashRepo: SplashRepo
) : ViewModel() {

    fun isUserLoggedIn(): Boolean {
        return splashRepo.isUserLoggedIn()
    }
}
