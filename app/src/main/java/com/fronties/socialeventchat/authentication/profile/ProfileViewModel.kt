package com.fronties.socialeventchat.authentication.profile

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation

class ProfileViewModel : ViewModel() {
    @Bindable
    val firstNameEtContent = MutableLiveData<String>()

    private val _firstNameForProfile = MutableLiveData<String>()
    val usernameForLogin: LiveData<String>
        get() = _firstNameForProfile

    @Bindable
    val lastNameEtContent = MutableLiveData<String>()

    private val _lastNameForProfile = MutableLiveData<String>()
    val lastNameForProfile: LiveData<String>
        get() = _lastNameForProfile

    @Bindable
    val phoneNumberEtContent = MutableLiveData<String>()

    private val _phoneNumberForProfile = MutableLiveData<String>()
    val phoneNumberForProfile: LiveData<String>
        get() = _phoneNumberForProfile

    fun saveProfileButtonClicked(){
        _firstNameForProfile.value = firstNameEtContent.value
        _lastNameForProfile.value = lastNameEtContent.value
        _phoneNumberForProfile.value = phoneNumberEtContent.value

//        Save User Profile
//        *** Uncomment Below ***
//        saveUserProfile(firstNameEtContent.value,lastNameEtContent.value,phoneNumberEtContent.value)

//        *** Write Code to Take User to Main Screen***
    }

    fun skipProfileButtonClicked(){
//        *** Write Code to Take User to Register Screen***
    }
}