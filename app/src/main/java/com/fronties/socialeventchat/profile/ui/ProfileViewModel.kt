package com.fronties.socialeventchat.profile.ui

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.*

import com.fronties.socialeventchat.helperClasses.Event
import com.fronties.socialeventchat.profile.model.User
import com.fronties.socialeventchat.profile.repo.ProfileRepo
import com.fronties.socialeventchat.profile.room.ProfileEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
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
    val listenerForProfileImage: LiveData<Event<Unit>>
        get() = _listenerForProfileImage

    val _profileImage = MutableLiveData<Bitmap>()
    val profileImage: LiveData<Bitmap>
        get() = _profileImage

    private val _profileImageUri = MutableLiveData<Event<Uri>>()
    val profileImageUri: LiveData<Event<Uri>>
        get() = _profileImageUri

    fun saveProfileButtonClicked() {

        saveUserProfile(
            firstNameEtContent.value,
            lastNameEtContent.value,
            phoneNumberEtContent.value,
            profileImage.value
        )

        updateProfile()

        _listenerForProfileToEventFeed.value = Event(Unit)
    }

    fun loadAll() = profileRepo.loadAllProfile()

    private fun saveUserProfile(
        firstName: String?,
        lastName: String?,
        phoneNumber: String?,
        profileImage: Bitmap?
    ) {
        val eachProfile = ProfileEntity(
            firstName = firstName!!, lastName = lastName!!, phoneNumber = phoneNumber!!
        )
        // TODO change this to just uri
        eachProfile.profilePic = profileImage

        viewModelScope.launch {
            profileRepo.saveUserProfile(eachProfile)
        }
    }

    fun profileImageClicked() {
        _listenerForProfileImage.value = Event(Unit)
    }

    private fun updateProfile() {
        val file = profileRepo.createImageFile(
            _profileImageUri.value?.getContentIfNotHandled()
        )
        viewModelScope.launch {

            val user = User(
                firstname = firstNameEtContent.value,
                lastname = lastNameEtContent.value,
                phonenumber = phoneNumberEtContent.value
            )
            profileRepo.updateProfile(file, user)
        }
    }

    fun setValueOfImageUri(imageUri: Uri?) {
        imageUri?.let {
            _profileImageUri.value = Event(it)
        }
    }

    // region remove after fix
    fun testImageFile(): File? {
        val file = profileRepo.getImageFile()
        return file
    }
    // endregion

    fun skipProfileButtonClicked() {}
}// class ends here
