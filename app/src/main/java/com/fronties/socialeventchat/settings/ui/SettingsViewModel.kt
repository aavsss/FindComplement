package com.fronties.socialeventchat.settings.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.helperClasses.Event
import com.fronties.socialeventchat.settings.repo.SettingsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepo: SettingsRepo
) : ViewModel() {

    private val _userLoggedOut = MutableLiveData(false)
    val userLoggedOut: LiveData<Boolean>
        get() = _userLoggedOut

    private val _listenerForUserProfile = MutableLiveData<Event<Unit>>()
    val listenerForUserProfile: LiveData<Event<Unit>>
        get() = _listenerForUserProfile

    fun viewUserProfile() {
        _listenerForUserProfile.value = Event(Unit)
    }

    // function to log user out
    fun logoutUser() {
        viewModelScope.launch {
            settingsRepo.logout()
        }

        _userLoggedOut.value = true
    }
}
