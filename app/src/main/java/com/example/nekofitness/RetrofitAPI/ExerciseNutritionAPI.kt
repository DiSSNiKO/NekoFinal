package com.example.nekofitness.RetrofitAPI

import com.example.nekofitness.DataClasses.Exercises
import com.example.nekofitness.DataClasses.FoodMacros
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ExerciseNutritionAPI {
    @Headers("X-Api-Key: " + "pLL/ZVxN3gvluw3EyUnS1g==avuHZJs0a50l7own")
    @GET("v1/exercises?")
    suspend fun fetchExercises(@Query("muscle")muscle:String): Response<ArrayList<Exercises>>
    @Headers("X-Api-Key: " + "pLL/ZVxN3gvluw3EyUnS1g==avuHZJs0a50l7own")
    @GET("v1/nutrition?")
    suspend fun fetchMacros(@Query("query")muscle:String): Response<ArrayList<FoodMacros>>
}