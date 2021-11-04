package com.fronties.socialeventchat.helperClasses.dateTime

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateTimeUtilsImpl @Inject constructor() : DateTimeUtils {

    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

    override fun getDateString(isoDate: String): String {
//        TODO("Not yet implemented")
        return "10/26/2021"
    }

    override fun getTimeString(isoDate: String): String {
//        TODO("Not yet implemented")
        return "7:00pm"
    }

    override fun getDateAndTimeString(isoDate: String): String {
//        TODO("Not yet implemented")
        return "10/26/2021 at 7:00pm"
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
