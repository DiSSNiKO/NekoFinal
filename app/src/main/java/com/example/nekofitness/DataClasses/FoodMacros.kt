package com.example.nekofitness.DataClasses

// Food macronutrient values based on a 100 gram serving size
data class FoodMacros(
    var name_and_amount: String = "",
    var calories: String = "",
    var serving_size_g: String = "",
    var fat_total_g: String = "",
    var fat_saturated_g: String = "",
    var protein_g: String = "",
    var sodium_mg: String = "",
    var potassium_mg: String = "",
    var cholesterol_mg: String = "",
    var carbohydrates_total_g: String = "",
    var fiber_g: String = "",
    var sugar_g: String = "",
)
