package com.fronties.socialeventchat.profile.ui

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.*

import com.fronties.socialeventchat.helperClasses.Event
import com.fronties.socialeventchat.profile.repo.ProfileRepo
import com.fronties.socialeventchat.profile.room.ProfileDatabase
import com.fronties.socialeventchat.profile.room.ProfileEntity
import com.fronties.socialeventchat.profile.room.ProfileExecutor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepo: ProfileRepo
) : ViewModel(), Observable {

    private val DEFAULT_GOAL_ID = -1
    private val mProfileId = DEFAULT_GOAL_ID

    // toggle skip button off for when we just want to view profile
    var showSkipButton = true


    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

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

    private val _listenerForNavToProfile = MutableLiveData<Event<Unit>>()
    val listenerForNavToProfile: LiveData<Event<Unit>>
        get() = _listenerForNavToProfile

    private val _listenerForProfileToEventFeed = MutableLiveData<Event<Unit>>()
    val listenerForProfileToEventFeed: LiveData<Event<Unit>>
        get() = _listenerForProfileToEventFeed

    fun saveProfileButtonClicked() {
        _firstNameForProfile.value = firstNameEtContent.value
        _lastNameForProfile.value = lastNameEtContent.value
        _phoneNumberForProfile.value = phoneNumberEtContent.value

//        Save User Profile
        saveUserProfile(
            firstNameEtContent.value,
            lastNameEtContent.value,
            phoneNumberEtContent.value
        )
        _listenerForProfileToEventFeed.value = Event(Unit)
//        *** Write Code to Take User to Main Screen***
    }


    private fun saveUserProfile(firstName: String?, lastName: String?, phoneNumber: String?) {
        val eachProfile = ProfileEntity(
            firstName = firstName!!, lastName = lastName!!, phoneNumber = phoneNumber!!
        )

        viewModelScope.launch {
            profileRepo.saveUserProfile(eachProfile)
        }
    }


        fun skipProfileButtonClicked() {
//        *** Write Code to Take User to Register Screen***
//        ProfileExecutor.getInstance()?.diskIO()?.execute(Runnable {
//            if (mProfileId == DEFAULT_GOAL_ID) {
//                mProfileDatabase?.profileDao()?.insertProfile(eachProfile)
//            } else {
//                eachProfile.id = mProfileId
//                mProfileDatabase?.profileDao()?.updateProfile(eachProfile)
//            }

//            var allProfiles = profileRepo.loadAllProfile()
//            println(allProfiles?.value)
//        })
            viewModelScope.launch {
                var allProfilessss = profileRepo.loadAllProfile()
                println(allProfilessss!!.value)
            }
        }

        fun goToMainScreen() {
            _listenerForNavToProfile.value = Event(Unit)
        }
    }// class ends here