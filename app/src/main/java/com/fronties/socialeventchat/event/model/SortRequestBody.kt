package com.fronties.socialeventchat.event.model

import com.fronties.socialeventchat.event.dependency.sorting.SortOrder
import com.fronties.socialeventchat.event.dependency.sorting.SortType

data class SortRequestBody(
    val sortType: SortType = SortType.NAME,
    val sortOrder: SortOrder = SortOrder.ASC
)
