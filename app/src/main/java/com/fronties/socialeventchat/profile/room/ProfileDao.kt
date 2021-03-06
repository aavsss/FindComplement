package com.fronties.socialeventchat.profile.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile")
    fun loadAllProfile(): LiveData<List<ProfileEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(eachProfile: ProfileEntity?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProfile(eachProfile: ProfileEntity?)

    @Delete
    suspend fun deleteProfile(eachProfile: ProfileEntity?)

    @Query("SELECT * FROM profile where id = :id")
    fun loadProfileById(id: Int): LiveData<ProfileEntity?>?
}
