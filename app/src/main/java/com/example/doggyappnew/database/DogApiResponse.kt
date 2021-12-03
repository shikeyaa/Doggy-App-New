package com.example.doggyappnew.database

import com.squareup.moshi.Json

data class DogApiResponse(
    @Json(name = "message")
    val message: String?)