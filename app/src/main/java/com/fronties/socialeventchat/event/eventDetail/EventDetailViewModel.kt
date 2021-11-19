
package com.fronties.socialeventchat.event.eventDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.event.model.SocialEvents
import com.fronties.socialeventchat.event.repo.EventRepo
import com.fronties.socialeventchat.helperClasses.Event
import com.fronties.socialeventchat.helperClasses.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    private val eventRepo: EventRepo
) : ViewModel() {

    private val _eventDetail = MutableLiveData<Resource<SocialEvents>>()
    val eventDetail: LiveData<Resource<SocialEvents>> = _eventDetail

    private val _attendSuccess = MutableLiveData<Resource<Boolean>>()
    val attendSuccess: LiveData<Resource<Boolean>> = _attendSuccess

    private val _errorViewListener = MutableLiveData<Event<Unit>>()
    val errorViewListener: LiveData<Event<Unit>> = _errorViewListener

    private val _navToChat = MutableLiveData<Event<Int>>()
    val navToChat: LiveData<Event<Int>> = _navToChat

    private val _navBack = MutableLiveData<Event<Unit>>()
    val navBack: LiveData<Event<Unit>> = _navBack

    private val _isHost = MutableLiveData<Boolean>()
    val isHost: LiveData<Boolean> = _isHost

    private var eid: Int = -1

    fun getEventDetails(eventID: Int) {
        setEid(eventID)
        viewModelScope.launch {
            val eventDetail = try {
                eventRepo.getEventDetails(eventID)
            } catch (e: Exception) {
                _errorViewListener.value = Event(Unit)
                return@launch
            }
            _eventDetail.value = Resource.success(eventDetail)
        }
    }

    private fun setEid(eventID: Int) {
        eid = eventID
    }

    // given an event ID indicate that the user is going
    fun attendEvent(eid: Int) {
        viewModelScope.launch {
            val isAttendingEvent = try {
                eventRepo.attendEvent(eid)
            } catch (e: Exception) {
                _errorViewListener.value = Event(Unit)
                return@launch
            }
            _attendSuccess.value = Resource.success(isAttendingEvent)
        }
    }

    fun goBack() {
        _navBack.value = Event(Unit)
    }

    fun goToChat() {
        _navToChat.value = Event(eid)
    }

    fun setIsHostTo(isHost: Boolean) {
        _isHost.value = isHost
    }
}
