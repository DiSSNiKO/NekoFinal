package com.example.nekofitness.RetrofitAPI

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : ExerciseNutritionAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExerciseNutritionAPI::class.java)
    }
}