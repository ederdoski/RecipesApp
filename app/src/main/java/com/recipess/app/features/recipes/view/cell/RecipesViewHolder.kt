package com.recipess.app.features.recipes.view.cell

import android.util.Log
import com.adrenastudies.trufisapp.core.base.BaseViewHolder
import com.adrenastudies.trufisapp.core.base.IOnItemClickViewHolder
import com.recipess.app.R
import com.recipess.app.core.extensions.setImageSrcFromUrl
import com.recipess.app.core.extensions.toGone
import com.recipess.app.core.extensions.toVisible
import com.recipess.app.databinding.RecipesViewCellBinding
import com.recipess.app.features.recipes.datasource.model.RecipeData

class RecipesViewHolder (
    private val binding: RecipesViewCellBinding,
    onItemClickListener: IOnItemClickViewHolder
) : BaseViewHolder<RecipeData>(binding, onItemClickListener) {

    override fun bindingDataInHolder(data: RecipeData) {
        super.bindingDataInHolder(data)

        setRating(data)
        binding.txtTitle.text = data.name
        binding.imgRecipe.setImageSrcFromUrl(data.thumbnail_url, R.mipmap.ic_launcher, binding.root.context)
    }

    private fun setRating(data: RecipeData) {
        if(data.user_ratings != null) {
            binding.lyRatingPositive.toVisible()
            binding.lyRatingNegative.toVisible()
            binding.txtPositive.text = data.user_ratings.count_positive.toString()
            binding.txtNegative.text = data.user_ratings.count_negative.toString()
        }else{
            binding.lyRatingPositive.toGone()
            binding.lyRatingNegative.toGone()
        }
    }

}
