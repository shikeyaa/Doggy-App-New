package com.example.doggyappnew

import android.app.Application
import androidx.room.Room
import com.example.doggyappnew.database.DogDao
import com.example.doggyappnew.database.DogDatabase
import okhttp3.internal.ignoreIoExceptions

class DogApplication : Application() {

    lateinit var database: DogApplication

    override fun onCreate() {
        super.onCreate()

        ApplicationManager.initialize(this)
    }


}

object ApplicationManager {
    private lateinit var application: Application

    // Initialize Room database
    val db by lazy {
        Room.databaseBuilder(
            this.application,
            DogDatabase::class.java,
            "app_database"
        ).build()
    }

    fun initialize(application: Application) {
        this.application = application
    }
}