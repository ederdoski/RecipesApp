package com.recipess.app.core.protocol

import com.recipess.app.features.recipes.datasource.model.RecipeData

sealed class ProtocolAction {
    object OnNetworkError : ProtocolAction()
    class OnLoading(val loading: Boolean) : ProtocolAction()
    object OnPlatformError : ProtocolAction()
    class OnGoToRecipeDetail(val data: RecipeData) : ProtocolAction()
    class OnGoToRecipeVideo(val url: String) : ProtocolAction()
}