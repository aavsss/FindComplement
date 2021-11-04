package com.fronties.socialeventchat.settings.ui

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    // function to log user out
    fun logoutUser() {
        viewModelScope.launch {
            settingsRepo.logout()
        }

        _userLoggedOut.value = true
    }
}