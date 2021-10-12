package com.fronties.socialeventchat.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fronties.socialeventchat.helperClasses.Event
import com.fronties.socialeventchat.splashscreen.repo.SplashRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashRepo: SplashRepo
) : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Event<Boolean>>()
    val isLoggedIn: LiveData<Event<Boolean>>
        get() = _isLoggedIn

    fun checkLogInStatus() {
        _isLoggedIn.value = Event(splashRepo.isUserLoggedIn())
    }
}
