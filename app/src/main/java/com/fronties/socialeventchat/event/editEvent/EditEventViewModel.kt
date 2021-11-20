package com.fronties.socialeventchat.event.editEvent

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.application.session.AuthException
import com.fronties.socialeventchat.event.repo.EventRepo
import com.fronties.socialeventchat.helperClasses.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class EditEventViewModel @Inject constructor(
    private val eventRepo: EventRepo
) : ViewModel(), Observable {
    val eventName = MutableLiveData<String>()
    val eventType = MutableLiveData<String>()
    val eventDescription = MutableLiveData<String>()
    val eventStartDate = MutableLiveData<Triple<Int, Int, Int>>()
    val eventStartTime = MutableLiveData<Pair<Int, Int>>()
    val eventEndDate = MutableLiveData<Triple<Int, Int, Int>>()
    val eventEndTime = MutableLiveData<Pair<Int, Int>>()
    val eventContactNumber = MutableLiveData<String>()
    val eventHost = MutableLiveData<String>()

    // want this to hold value of our eid
    private var eid = 0

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    private val _listenerForStartDatePicker = MutableLiveData<Event<Unit>>()
    val listenerForStartDatePictureStyle: LiveData<Event<Unit>>
        get() = _listenerForStartDatePicker

    private val _listenerForStartTimePicker = MutableLiveData<Event<Unit>>()
    val listenerForStartTimePictureStyle: LiveData<Event<Unit>>
        get() = _listenerForStartTimePicker

    private val _listenerForEndDatePicker = MutableLiveData<Event<Unit>>()
    val listenerForEndDatePictureStyle: LiveData<Event<Unit>>
        get() = _listenerForEndDatePicker

    private val _listenerForEndTimePicker = MutableLiveData<Event<Unit>>()
    val listenerForEndTimePictureStyle: LiveData<Event<Unit>>
        get() = _listenerForEndTimePicker

    private val _listenerForAddedEvent = MutableLiveData<Event<Unit>>()
    val listenerForAddedEvent: LiveData<Event<Unit>>
        get() = _listenerForAddedEvent

    private val _listenerForError = MutableLiveData<Event<String>>()
    val listenerForError: LiveData<Event<String>>
        get() = _listenerForError

    fun initialSetup(eid: Int) {
        this.eid = eid
    }

    fun setStartDate(year: Int, month: Int, day: Int) {
        eventStartDate.value = Triple(year, month, day)
    }

    fun setStartTime(hourOfDay: Int, minute: Int) {
        eventStartTime.value = Pair(hourOfDay, minute)
    }

    fun setEndDate(year: Int, month: Int, day: Int) {
        eventEndDate.value = Triple(year, month, day)
    }

    fun setEndTime(hourOfDay: Int, minute: Int) {
        eventEndTime.value = Pair(hourOfDay, minute)
    }

    fun pickStartDate() {
        _listenerForStartDatePicker.value = Event(Unit)
    }

    fun pickStartTime() {
        _listenerForStartTimePicker.value = Event(Unit)
    }

    fun pickEndDate() {
        _listenerForEndDatePicker.value = Event(Unit)
    }

    fun pickEndTime() {
        _listenerForEndTimePicker.value = Event(Unit)
    }

    fun addEvent() {
        viewModelScope.launch {
            try {
                if (eventRepo.addEvent(
                        eventName.value,
                        eventDescription.value,
                        eventType.value,
                        eventContactNumber.value,
                        eventStartDate.value,
                        eventStartTime.value,
                        eventEndDate.value,
                        eventEndTime.value,
                        eventHost.value
                    )
                ) {
                    _listenerForAddedEvent.value = Event(Unit)
                } else {
                    _listenerForError.value = Event("Sorry! Error occurred")
                }
            } catch (e: AuthException) {
                // TODO go to login screen
                _listenerForError.value = Event(e.localizedMessage ?: "Unknown Error")
                return@launch
            } catch (e: Exception) { // could all of this just be 1 excpetion?
                _listenerForError.value = Event(e.localizedMessage ?: "Unknown Error")
                return@launch
            }
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }
}
