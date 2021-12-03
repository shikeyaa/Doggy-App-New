package com.example.doggyappnew.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.doggyappnew.network.Dog

@Dao
interface DogDao {

    @Query("SELECT * FROM dog")
    fun getAll(): LiveData<List<Dog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg dog: Dog)

    @Query("SELECT * FROM dog ORDER BY id DESC LIMIT 1")
    fun getMostRecentlyAddDog() : Dog

    @Query("DELETE from dog where image_url=(select max(image_url)-1 from dog)")
    suspend fun deleteDog()
   // fun addDogImage(dogDao: DogDao)

    // @Insert
   // suspend fun addDogImage(dogDao: DogDao)
   // fun getAll(): LiveData<List<Dog>>
}