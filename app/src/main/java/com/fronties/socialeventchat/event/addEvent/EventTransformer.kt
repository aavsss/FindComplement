package com.fronties.socialeventchat.event.addEvent

import com.fronties.socialeventchat.event.model.SocialEvents

interface EventTransformer {

    fun checkRequiredItems(socialEvents: SocialEvents): Boolean
    fun transformDateToUTC(
        date: Triple<Int, Int, Int>?,
        time: Pair<Int, Int>?
    ): String?
}
