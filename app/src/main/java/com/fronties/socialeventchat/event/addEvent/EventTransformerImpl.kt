package com.fronties.socialeventchat.event.addEvent

import com.fronties.socialeventchat.application.phoneValidator.PhoneNumberValidator
import com.fronties.socialeventchat.event.model.SocialEvents
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventTransformerImpl @Inject constructor() : EventTransformer {
    // returns required properties of Event.
    // returning list of required items for now for future references
    override fun checkRequiredItems(socialEvents: SocialEvents): List<String> {
        val requiredItems = mutableListOf<String>()
        if (socialEvents.name == null) {
            requiredItems.add("Name")
        }
        if (socialEvents.hostname == null) {
            requiredItems.add("HostName")
        }
        return requiredItems
    }

    override fun transformDateToUTC(
        date: Triple<Int, Int, Int>?,
        time: Pair<Int, Int>?
    ): String? {
        val calendar = Calendar.getInstance()
        return if (date != null && time != null) {
            calendar.set(date.first, date.second, date.third, time.first, time.second)
            calendar.set(Calendar.MILLISECOND, 0)
            val dateFromCalendar = calendar.time
            convertToISO8601(dateFromCalendar)
        } else if (date != null) {
            calendar.set(date.first, date.second, date.third)
            calendar.set(Calendar.MILLISECOND, 0)
            val dateFromCalendar = calendar.time
            convertToISO8601(dateFromCalendar)
        } else {
            null
        }
    }

    private fun convertToISO8601(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        return format.format(date)
    }
}
