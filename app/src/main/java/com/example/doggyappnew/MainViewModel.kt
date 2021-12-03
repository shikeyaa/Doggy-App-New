package com.example.doggyappnew

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.doggyappnew.database.DogDao
import com.example.doggyappnew.network.Dog
import com.example.doggyappnew.repository.DogRepository
import kotlinx.coroutines.launch

class MainViewModel(private val dogDao: DogDao) : ViewModel() {

    val repository: DogRepository = DogRepository()
    internal val dogList: LiveData<List<Dog>> = repository.getDogs()

    init {
        getRandomDog()
    }
    fun getRandomDog() {
        viewModelScope.launch {

           // dogList.value = listOf(DogApi.retrofitService.getRandomDogImage())
        }
    }
    fun getAll()
    {
        viewModelScope.launch {
           // dogDao.addDogImage(dogDao)
        }
    }

    fun getAllDogsImages(dogDao: DogDao) {
        dogDao.getMostRecentlyAddDog()

    }
    //fun deleteMostRecentDog() {
       // viewModelScope.launch {
       //     dogDao.deleteDog()
       // }
    }
    //fun getAllDogs(): LiveData<List<Dog>>
    //{
    // return MainActivity.dogDao.getAllDogs().asLiveData()
    //}

    class MainViewModelFactory(
        private val dogDao: DogDao
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(dogDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

