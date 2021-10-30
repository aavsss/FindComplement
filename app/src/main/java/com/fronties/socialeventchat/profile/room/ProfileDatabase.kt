package com.fronties.socialeventchat.profile.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [ProfileEntity::class], version = 1, exportSchema = false)
@TypeConverters(BitmapConverters::class)
abstract class ProfileDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    companion object {
        private val LOCK = Object()

        // This is a singleton object that represents the database instance used throughout the app
        @Volatile
        private var sInstance: ProfileDatabase? = null

        private val TAG = ProfileDatabase::class.java.simpleName
        private val DB_NAME = "profile"

        var migrationOneToTwo: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'profile' ADD COLUMN 'id' INTEGER")
            }
        }

        fun getInstance(context: Context): ProfileDatabase {
            // return non-null INSTANCE, if INSTANCE is null then initialize it
            return sInstance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProfileDatabase::class.java,
                    "word_database"
                ).build()
                sInstance = instance
                instance
            }
        }
    }
}