package com.fronties.socialeventchat.profile.room

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "profile")
class ProfileEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Int? = 0,
    @ColumnInfo(name = "firstName") var firstName: String,
    @ColumnInfo(name = "lastName") var lastName:String,
    @ColumnInfo(name = "phoneNumber") var phoneNumber: String?
)
{
    var profilePic: Uri? = null
}