package com.fronties.socialeventchat.event.addEvent

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
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
import java.util.Date
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val eventRepo: EventRepo
) : ViewModel(), Observable {

    val eventName = MutableLiveData<String>()
    val eventType = MutableLiveData<String>()
    val eventDescription = MutableLiveData<String>()
    val eventStartDate = MutableLiveData<String>()
    val eventStartTime = MutableLiveData<String>()
    val eventEndDate = MutableLiveData<String>()
    val eventEndTime = MutableLiveData<String>()
    val eventContactNumber = MutableLiveData<String>()
    val eventHost = MutableLiveData<String>()

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

    fun initialSetup() {
//        eventStartDate.value = Date().toString()
//        eventEndDate.value = Date().toString()
    }

    fun setStartDate(year: Int, month: Int, day: Int) {
        eventStartDate.value = "$year/$month/$day"
    }

    fun setStartTime(hourOfDay: Int, minute: Int) {
        eventStartTime.value = "$hourOfDay/$minute"
    }

    fun setEndDate(year: Int, month: Int, day: Int) {
        eventEndDate.value = "$year/$month/$day"
    }

    fun setEndTime(hourOfDay: Int, minute: Int) {
        eventEndTime.value = "$hourOfDay/$minute"
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
        val socialEvents = SocialEvents(
            eid = Random.nextInt(0, 10000),
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

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }
}
