package com.recipess.app.features.recipes.datasource.model

import java.io.Serializable

data class RecipeDataResponse(
    val results: ArrayList<RecipeData>,
)

data class RecipeData (
    val id: String,
    val name:String,
    val video_url: String,
    val description: String,
    val user_ratings: Rating?,
    val thumbnail_url: String,
    val prep_time_minutes: Int,
    val cook_time_minutes: Int,
    val instructions: ArrayList<Instructions>?,
): Serializable

data class Instructions(
    val display_text: String,
): Serializable

data class Rating(
    val score: Float,
    val count_positive: Int,
    val count_negative: Int,
): Serializable


