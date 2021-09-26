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

    private val _errorViewListener = MutableLiveData<Event<Unit>>()
    val errorViewListener: LiveData<Event<Unit>> = _errorViewListener

    fun getEventDetails(eventID: Int) {
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
}
