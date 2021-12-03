package com.example.doggyappnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.net.toUri
import coil.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    class dogDao {
        fun addDogImage(dogDao: dogDao) {
        }
        var name: String = "DogDao"
    }
    private val viewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as DogApplication).database.dogDao())
    }

    private var breedText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val prevDogButton: Button = findViewById(R.id.prevDogButton)

        viewModel.dogList.observe(this, { dogList ->
            if (!dogList.isNullOrEmpty()) {
                Log.d("MainActivity", "$dogList")
                findViewById<ImageView>(R.id.ivSrcImg).load(
                    dogList.last().message.toUri().buildUpon().scheme("https").build()
                ) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_connection_error)
                }
            }
        })

        findViewById<Button>(R.id.btnRandomImg).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.repository.getRandomDog()
            }
        }

        findViewById<Button>(R.id.btnBreedImg).setOnClickListener {
            breedText = findViewById<EditText>(R.id.etBreed).text.toString()
            findViewById<EditText>(R.id.etBreed).setText("")
        }
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show()


        prevDogButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

}