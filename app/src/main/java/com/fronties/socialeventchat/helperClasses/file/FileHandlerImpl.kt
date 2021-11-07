package com.fronties.socialeventchat.helperClasses.file

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import javax.inject.Inject

class FileHandlerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : FileHandler {

    override fun getFile(fileType: FileType, fileName: String): File? {
        val mediaStorageDir = File(
            context.getExternalFilesDir(null)?.absoluteFile,
            fileType.type
        )

        return File(mediaStorageDir, "$fileName.jpg")
    }

    override fun createFile(
        imageUri: Uri?,
        fileName: String?,
        fileType: FileType
    ): File? {
        imageUri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(
                context.contentResolver, imageUri
            )
            return persistImage(bitmap, fileName, fileType)
        }
        return null
    }

    private fun persistImage(
        bitmap: Bitmap?,
        name: String?,
        fileType: FileType
    ): File? {
        val mediaStorageDir = File(
            context.getExternalFilesDir(null)?.absoluteFile,
            fileType.type
        )

        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdir()
        }

        val imageFile = File(mediaStorageDir, "$name.jpg")
        val os: OutputStream
        try {
            val bytes = ByteArrayOutputStream()
            os = FileOutputStream(imageFile)
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 60, bytes)
            os.write(bytes.toByteArray())
            os.flush()
            os.close()
            return imageFile
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "Error writing bitmap", e)
        }
        return null
    }
}
