package com.recipess.app.features.recipes.view

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.recipess.app.R
import com.recipess.app.core.protocol.ProtocolAction
import com.recipess.app.databinding.LayoutRecipesViewActivityBinding
import com.recipess.app.features.recipes.datasource.model.RecipeData
import com.salmo23.bibliaydevocional.core.base.BaseActivity

class RecipesViewActivity : BaseActivity<LayoutRecipesViewActivityBinding>() {

    private val navController by lazy { binding.content.findNavController() }

    override fun onFragmentEvent(action: ProtocolAction) {
        when (action) {
            is ProtocolAction.OnGoToRecipeDetail -> goToRecipeDetail(action.data)
            is ProtocolAction.OnGoToRecipeVideo -> goToRecipeVideo(action.url)
            else -> {}
        }
    }

    override fun init() {}

    private fun goToRecipeDetail(data: RecipeData) {
        val bundle = bundleOf("recipeData" to data)
        goTo(navController, R.id.action_RecipesViewFragment_to_RecipesDetailViewFragment, bundle)
    }

    private fun goToRecipeVideo(data: String) {
        val bundle = bundleOf("url" to data)
        goTo(navController, R.id.action_RecipesDetailViewFragment_to_RecipesVideoViewFragment, bundle)
    }
}