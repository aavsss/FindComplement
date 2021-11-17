package com.fronties.socialeventchat.event.myEvent

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.application.session.AuthException
import com.fronties.socialeventchat.event.EventViewModel
import com.fronties.socialeventchat.event.dependency.sorting.SortOrder
import com.fronties.socialeventchat.event.dependency.sorting.SortType
import com.fronties.socialeventchat.event.model.SocialEvents
import com.fronties.socialeventchat.event.repo.EventRepo
import com.fronties.socialeventchat.helperClasses.Event
import com.fronties.socialeventchat.helperClasses.Resource
import com.fronties.socialeventchat.profile.repo.ProfileRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MyEventViewModel @Inject constructor(
    private val eventRepo: EventRepo,
    private val profileRepo: ProfileRepo
) : EventViewModel() {

    private val _eventList = MutableLiveData<Resource<List<SocialEvents>>>()
    val eventList: LiveData<Resource<List<SocialEvents>>>
        get() = _eventList

    private val _errorViewListener = MutableLiveData<Event<Unit>>()
    val errorViewListener: LiveData<Event<Unit>> = _errorViewListener

    private val _profilePic = MutableLiveData<Event<Uri?>>()
    val profilePic: LiveData<Event<Uri?>>
        get() = _profilePic

    private val _listenerForSort = MutableLiveData<Event<Unit>>()
    val listenerForSort: LiveData<Event<Unit>>
        get() = _listenerForSort

    fun getMyEventList() {
        viewModelScope.launch {
            _eventList.value = Resource.loading(emptyList())
            val eventsList = try {
                eventRepo.getMyEvents()
            } catch (e: AuthException) {
                // TODO go to login screen
                _errorViewListener.value = Event(Unit)
                return@launch
            } catch (e: Exception) {
                _errorViewListener.value = Event(Unit)
                return@launch
            }
            _eventList.value = Resource.success(eventsList)
        }
    }

    fun showSort() {
        _listenerForSort.value = Event(Unit)
    }

    fun sortEvents(sortType: SortType, sortOrder: SortOrder) {
        viewModelScope.launch {
            try {
                val sortedEvents = eventRepo.sortEvents(sortType = sortType, sortOrder = sortOrder)
                _eventList.value = Resource.success(sortedEvents)
            } catch (e: Exception) {
                _errorViewListener.value = Event(Unit)
                return@launch
            }
        }
    }

    fun loadProfilePic() {
        _profilePic.value = Event(profileRepo.getImageFile()?.toUri())
    }
}
