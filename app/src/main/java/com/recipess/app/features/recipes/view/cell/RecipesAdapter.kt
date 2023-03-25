package com.recipess.app.features.recipes.view.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import com.adrenastudies.trufisapp.core.base.BaseAdapter
import com.adrenastudies.trufisapp.core.base.BaseViewHolder
import com.adrenastudies.trufisapp.core.base.IOnItemClickViewHolder
import com.recipess.app.databinding.RecipesViewCellBinding
import com.recipess.app.features.recipes.datasource.model.RecipeData
import com.recipess.app.features.recipes.datasource.model.RecipeDataResponse

class RecipesAdapter (
    dataList: List<RecipeData> = mutableListOf(),
    private val onItemClickListener: IOnItemClickViewHolder
) : BaseAdapter<RecipeData>(dataList, onItemClickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RecipeData> {
        return RecipesViewHolder(
            RecipesViewCellBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onItemClickListener
        )
    }
}