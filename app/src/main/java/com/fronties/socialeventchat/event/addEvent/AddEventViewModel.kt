package com.fronties.socialeventchat.event.addEvent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.event.model.SocialEvents
import com.fronties.socialeventchat.event.repo.EventRepo
import com.fronties.socialeventchat.helperClasses.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val eventRepo: EventRepo
) : ViewModel() {

    val eventName = MutableLiveData<String>()
    val eventType = MutableLiveData<String>()
    val eventDescription = MutableLiveData<String>()
    val eventStartDate = MutableLiveData<String>()
    val eventEndDate = MutableLiveData<String>()
    val eventContactNumber = MutableLiveData<String>()
    val eventHost = MutableLiveData<String>()

    private val _listenerForStartDatePicker = MutableLiveData<Event<Unit>>()
    val listenerForStartDatePictureStyle: LiveData<Event<Unit>>
        get() = _listenerForStartDatePicker

    private val _listenerForEndDatePicker = MutableLiveData<Event<Unit>>()
    val listenerForEndDatePictureStyle: LiveData<Event<Unit>>
        get() = _listenerForStartDatePicker

    private val _listenerForAddedEvent = MutableLiveData<Event<Unit>>()
    val listenerForAddedEvent: LiveData<Event<Unit>>
        get() = _listenerForAddedEvent

    fun initialSetup() {
        eventStartDate.value = Date().toString()
        eventEndDate.value = Date().toString()
    }

    fun setStartDate() {
        _listenerForStartDatePicker.value = Event(Unit)
    }

    fun setEndDate() {
        _listenerForEndDatePicker.value = Event(Unit)
    }

    fun addEvent() {
        val socialEvents = SocialEvents(
            eventName = eventName.value,
            eventType = eventType.value,
            eventDescription = eventDescription.value,
            eventHost = eventHost.value // TODO use inverseBindingAdapter to get all remaining properties
        )
        viewModelScope.launch {
            try {
                if (eventRepo.addEvent(socialEvents)) {
                    _listenerForAddedEvent.value = Event(Unit)
                }
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }
        }
    }
}
