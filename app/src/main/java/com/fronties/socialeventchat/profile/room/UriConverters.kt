package com.fronties.socialeventchat.profile.room

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.net.toUri
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class UriConverters {

    @TypeConverter
    fun fromUri(uri: Uri?): String {
        val outputStream = ByteArrayOutputStream()
        if (uri != null) {
            return uri.toString()
        }
        return ""
    }

    @TypeConverter
    fun toUri(string: String?): Uri? {
        return string?.let { string.toUri()}
    }
}