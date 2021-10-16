package com.fronties.socialeventchat.event.addEvent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.event.model.SocialEvents
import com.fronties.socialeventchat.event.repo.EventRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val eventRepo: EventRepo
) : ViewModel() {

    val eventName = MutableLiveData<String>()
    val eventType = MutableLiveData<String>()
    val eventDescription = MutableLiveData<String>()
    val eventStartDate = MutableLiveData<Date>()
    val eventEndDate = MutableLiveData<Date>()
    val eventContactNumber = MutableLiveData<String>()
    val eventHost = MutableLiveData<String>()

    fun addEvent() {
        val socialEvents = SocialEvents(
            eventName = eventName.value,
            eventType = eventType.value,
            eventDescription = eventDescription.value,
            eventHost = eventHost.value // TODO use inverseBindingAdapter to get all the values
        )
        viewModelScope.launch {
            try {
                eventRepo.addEvent(socialEvents)
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }
        }
    }
}
