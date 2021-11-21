package com.fronties.socialeventchat.helperClasses.dateTime

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateTimeUtilsImpl @Inject constructor() : DateTimeUtils {

    override fun getDateString(isoDate: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        return try {
            val date = format.parse(isoDate)
            val calendar = Calendar.getInstance()
            calendar.time = date!!
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DATE)
            val year = calendar.get(Calendar.YEAR)
            "$month/$day/$year"
        } catch (e: Exception) {
            "Unknown date"
        }
    }

    override fun getTimeString(isoDate: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        return try {
            val date = format.parse(isoDate)
            val calendar = Calendar.getInstance()
            calendar.time = date!!
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)
            val isAm = calendar.get(Calendar.AM_PM)
            if (isAm == 0) {
                if (minute < 10) {
                    "$hour:${minute}0 AM"
                } else {
                    "$hour:$minute AM"
                }
            } else {
                if (minute < 10) {
                    "$hour:${minute}0 PM"
                } else {
                    "$hour:$minute PM"
                }
            }
        } catch (e: Exception) {
            "Unknown time"
        }
    }

    override fun getDateAndTimeString(isoDate: String): String {
        return try {
            val dateString = getDateString(isoDate)
            val timeString = getTimeString(isoDate)
            if (timeString == "0:00 PM" || timeString == "0:00 AM") {
                dateString
            } else {
                "$dateString at $timeString"
            }
        } catch (e: Exception) {
            "Unknown date and time"
        }
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
