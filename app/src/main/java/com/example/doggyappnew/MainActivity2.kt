package com.example.doggyappnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.net.toUri
import coil.load
import com.example.doggyappnew.database.DogDao

class MainActivity2 : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as DogApplication).database.dogDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



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
       /* viewModel.getAllDogs().observe(this) {
            val imageView = findViewById<ImageView>(R.id.imageView2)
            Picasso.with(this).load(it.get[0].addDogImage).into(imageView)
            val button: Button = findViewById(R.id.currButton)
            button.setOnClickListener {
                finish()
            }
        }*/
    }
}

class Picasso {

}

fun DogApplication.dogDao(): DogDao {
    TODO("Not yet implemented")
}
