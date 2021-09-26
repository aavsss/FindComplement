package com.fronties.socialeventchat.event.eventDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.event.model.SocialEvents
import com.fronties.socialeventchat.event.repo.EventRepo
import com.fronties.socialeventchat.helperClasses.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    private val eventRepo: EventRepo
) : ViewModel() {

    private val _eventDetail = MutableLiveData<SocialEvents>()
    val eventDetail: LiveData<SocialEvents> = _eventDetail

    fun getEventDetails(eventID: Int) {
        viewModelScope.launch {
            val eventDetail = try {
                eventRepo.getEventDetails(eventID)
            } catch (e: Exception) {
                // TODO Show an empty view
                return@launch
            }
            _eventDetail.value = eventDetail
        }
    }
}
