package com.recipess.app.features.recipes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipess.app.core.network.NetworkResponse
import com.recipess.app.features.recipes.datasource.model.RecipeData
import com.recipess.app.features.recipes.datasource.model.RecipeDataResponse
import com.recipess.app.features.recipes.usecase.RecipesUseCase
import kotlinx.coroutines.launch
import java.net.HttpURLConnection

class RecipesViewModel (
    private val recipesUseCase: RecipesUseCase,
    val recipesVMDelegate: RecipesVMDelegate
) : ViewModel() {

    lateinit var recipeList : RecipeDataResponse

    fun getListOfRecipes() {
        viewModelScope.launch(recipesVMDelegate.exceptionHandler()) {
            recipesVMDelegate.loadingPostValue(true)
            val response = NetworkResponse(recipesUseCase.getListOfRecipes())
            if (response.network.httpCode == HttpURLConnection.HTTP_OK) {
                recipeList = response.data!!
                recipesVMDelegate.onRecipeListResponsePostValue(response.data!!)
            } else {
                recipesVMDelegate.showUnknownErrorPostValue(Throwable(response.network.message))
            }
        }
    }

    fun filterRecipeBy(text:String) {
        val newListData : ArrayList<RecipeData> = arrayListOf()
        recipeList.results.forEach {
            if(it.name.lowercase().contains(text.lowercase())) {
                newListData.add(it)
            }
        }
        recipesVMDelegate.onRecipeListFilterResponsePostValue(newListData)
    }
}