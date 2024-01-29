package com.example.nekofitness.DataClasses

// Food macronutrient values
data class ArchiveFoodMacros(
    var name_and_amount: String = "",
    var calories: String = "",
    var fat_total_g: String = "",
    var protein_g: String = "",
    var carbohydrates_total_g: String = "",
    var fiber_g: String = "",
    var addDate: String = ""
)
