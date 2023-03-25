package com.recipess.app.features.recipes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adrenastudies.trufisapp.core.base.BaseVMDelegate
import com.recipess.app.features.recipes.datasource.model.RecipeData
import com.recipess.app.features.recipes.datasource.model.RecipeDataResponse

class RecipesVMDelegate : BaseVMDelegate() {

    private val _onRecipeListResponse = MutableLiveData<RecipeDataResponse>()
    val onRecipeListResponse: LiveData<RecipeDataResponse> get() = _onRecipeListResponse
    fun onRecipeListResponsePostValue(data: RecipeDataResponse) {
        _onRecipeListResponse.postValue(data)
    }

    private val _onRecipeListFilterResponse = MutableLiveData<ArrayList<RecipeData>>()
    val onRecipeListFilterResponse: LiveData<ArrayList<RecipeData>> get() = _onRecipeListFilterResponse
    fun onRecipeListFilterResponsePostValue(data: ArrayList<RecipeData>) {
        _onRecipeListFilterResponse.postValue(data)
    }
}