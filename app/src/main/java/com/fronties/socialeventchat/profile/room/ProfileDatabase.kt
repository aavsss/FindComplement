package com.fronties.socialeventchat.profile.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [ProfileEntity::class], version = 1, exportSchema = false)
public abstract class ProfileDatabase : RoomDatabase()
{

    companion object{
        private val LOCK = Object()
        private var sInstance: ProfileDatabase? = null
        private val TAG = ProfileDatabase::class.java.simpleName
        private val DB_NAME = "profile"

        var migrationOneToTwo: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'profile' ADD COLUMN 'id' INTEGER")
            }
        }

//        fun getInstance(context: Context): ProfileDatabase? {
//            if(sInstance == null){
//                synchronized(LOCK) {
//                    Log.d(
//                        TAG,
//                        "Creating new database instance"
//                    )
//                    sInstance = Room.databaseBuilder(
//                        context.applicationContext,
//                        ProfileDatabase::class.java,
//                        DB_NAME
//                    ).build()
//                }
//            }
//            Log.d(TAG,"Getting the instance")
//            return sInstance
//        }
    }


    abstract fun profileDao(): ProfileDao
    
}