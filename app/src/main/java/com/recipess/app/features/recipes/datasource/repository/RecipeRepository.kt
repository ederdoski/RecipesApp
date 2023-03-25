package com.recipess.app.features.recipes.datasource.repository

import com.recipess.app.features.recipes.datasource.model.RecipeDataResponse
import com.recipess.app.features.recipes.datasource.service.RecipeService
import retrofit2.Response

class RecipeRepository(private val recipeService: RecipeService) {

    suspend fun getListOfRecipes(): Response<RecipeDataResponse> =
        recipeService.getListOfRecipes()

}