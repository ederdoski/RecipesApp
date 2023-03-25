package com.recipess.app.features.recipes.datasource.service

import com.recipess.app.features.recipes.datasource.model.RecipeDataResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RecipeService {

    @GET("recipes/list")
    suspend fun getListOfRecipes(): retrofit2.Response<RecipeDataResponse>

}
