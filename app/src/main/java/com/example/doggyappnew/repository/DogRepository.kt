package com.example.doggyappnew.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.doggyappnew.ApplicationManager
import com.example.doggyappnew.network.Dog
import com.example.doggyappnew.network.DogApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class DogRepository {
    private val serviceScope = CoroutineScope(Dispatchers.Default)

    init {
        serviceScope.launch (Dispatchers.IO) {
            getRandomDog()
        }
    }

    suspend fun getRandomDog() {
        try {
            val dog = DogApi.retrofitService.getRandomDogImage()
            ApplicationManager.db.dogDao().insertAll(dog)
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error occurred: ${e.message}")
        }
    }

    fun getDogs(): LiveData<List<Dog>> {
        return ApplicationManager.db.dogDao().getAll()
    }
}