package com.fronties.socialeventchat.profile.ui

import android.app.Application
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.provider.MediaStore
import androidx.core.app.ActivityCompat.startActivityForResult
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

    // flag for if we are CREATING profile (editMode false) or UPDATING profile (editMode true)
    var editMode = false

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    @Bindable
    val firstNameEtContent = MutableLiveData<String>()

    @Bindable
    val lastNameEtContent = MutableLiveData<String>()

    @Bindable
    val phoneNumberEtContent = MutableLiveData<String>()

    private val _listenerForProfileToEventFeed = MutableLiveData<Event<Unit>>()
    val listenerForProfileToEventFeed: LiveData<Event<Unit>>
        get() = _listenerForProfileToEventFeed

    private val _listenerForProfileImage = MutableLiveData<Event<Unit>>()
    val listenerForProfileImage : LiveData<Event<Unit>>
        get() = _listenerForProfileImage

    val _profileImage = MutableLiveData<Bitmap>()
    val profileImage : LiveData<Bitmap>
        get() = _profileImage

    fun saveProfileButtonClicked() {
        saveUserProfile(
            firstNameEtContent.value,
            lastNameEtContent.value,
            phoneNumberEtContent.value,
            profileImage.value
        )
        _listenerForProfileToEventFeed.value = Event(Unit)
    }

    fun loadAll() = profileRepo.loadAllProfile()

    private fun saveUserProfile(firstName: String?, lastName: String?, phoneNumber: String?, profileImage: Bitmap?) {
        val eachProfile = ProfileEntity(
            firstName = firstName ?: "First", lastName = lastName ?: "Last", phoneNumber = phoneNumber ?: "903"
        )
        eachProfile.profilePic = profileImage

        viewModelScope.launch {
            profileRepo.saveUserProfile(eachProfile)
        }
    }

    fun profileImageClicked(){
        _listenerForProfileImage.value = Event(Unit)
    }

    fun skipProfileButtonClicked() {
        _listenerForProfileImage.value = Event(Unit)
    }
}// class ends here