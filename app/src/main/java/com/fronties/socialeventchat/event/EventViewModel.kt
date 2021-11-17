package com.fronties.socialeventchat.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.event.dependency.sorting.SortOrder
import com.fronties.socialeventchat.event.dependency.sorting.SortType
import com.fronties.socialeventchat.helperClasses.Event
import com.fronties.socialeventchat.helperClasses.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class EventViewModel @Inject constructor(
    // TODO
) : ViewModel() {
    val sortOrder = MutableLiveData<SortOrder?>()
}
