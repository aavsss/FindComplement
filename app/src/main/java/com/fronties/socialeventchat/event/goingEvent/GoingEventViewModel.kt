package com.fronties.socialeventchat.event.goingEvent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.application.session.AuthException
import com.fronties.socialeventchat.event.model.SocialEvents
import com.fronties.socialeventchat.event.repo.EventRepo
import com.fronties.socialeventchat.helperClasses.Event
import com.fronties.socialeventchat.helperClasses.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class GoingEventViewModel @Inject constructor(
    private val eventRepo: EventRepo
) : ViewModel() {

    private val _eventList = MutableLiveData<Resource<List<SocialEvents>>>()
    val eventList: LiveData<Resource<List<SocialEvents>>>
        get() = _eventList

    private val _errorViewListener = MutableLiveData<Event<Unit>>()
    val errorViewListener: LiveData<Event<Unit>> = _errorViewListener

    fun getGoingEventList() {
        viewModelScope.launch {
            val eventsList = try {
                eventRepo.getGoingEvents()
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
}
