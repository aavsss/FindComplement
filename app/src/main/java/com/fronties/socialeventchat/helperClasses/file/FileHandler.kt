package com.fronties.socialeventchat.helperClasses.file

import android.net.Uri
import java.io.File

interface FileHandler {
    fun createFile(
        imageUri: Uri?,
        fileName: String? = null,
        fileType: FileType
    ): File?

    fun getFile(fileType: FileType, fileName: String): File
}
