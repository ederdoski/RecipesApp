package com.recipess.app.features.recipes.usecase

import com.recipess.app.features.recipes.datasource.model.RecipeDataResponse
import com.recipess.app.features.recipes.datasource.repository.RecipeRepository

class RecipesUseCase(private val recipeRepository: RecipeRepository) {

    suspend fun getListOfRecipes(): retrofit2.Response<RecipeDataResponse> =
        recipeRepository.getListOfRecipes()

}