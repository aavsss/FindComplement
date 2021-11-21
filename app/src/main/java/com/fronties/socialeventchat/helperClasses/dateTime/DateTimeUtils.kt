package com.fronties.socialeventchat.helperClasses.dateTime

interface DateTimeUtils {
    fun getDateString(isoDate: String): String
    fun getTimeString(isoDate: String): String
    fun getChatTimeString(isoDate: String): String
    fun getDateAndTimeString(isoDate: String): String
    fun transformDateToUTC(
        date: Triple<Int, Int, Int>?,
        time: Pair<Int, Int>?
    ): String?
}
