package com.recipess.app.features.recipes.view

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.adrenastudies.trufisapp.core.base.BaseFragment
import com.adrenastudies.trufisapp.core.base.IOnItemClickViewHolder
import com.recipess.app.core.extensions.observe
import com.recipess.app.core.extensions.toGone
import com.recipess.app.core.extensions.toVisible
import com.recipess.app.core.protocol.ProtocolAction
import com.recipess.app.databinding.LayoutRecipesViewFragmentBinding
import com.recipess.app.features.recipes.datasource.model.RecipeData
import com.recipess.app.features.recipes.datasource.model.RecipeDataResponse
import com.recipess.app.features.recipes.view.cell.RecipesAdapter
import com.recipess.app.features.recipes.viewmodel.RecipesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecipesViewFragment  : BaseFragment<LayoutRecipesViewFragmentBinding>() {

    companion object {
        const val FILTER_CHARACTER_SIZE = 1
    }

    private val recipeViewModel: RecipesViewModel by viewModel()

    //---- Base functions
    override fun screenName() = "RecipesViewFragment"

    override fun listenToObserver() {
        observe(recipeViewModel.recipesVMDelegate.onNetworkError, this::onNetworkError)
        observe(recipeViewModel.recipesVMDelegate.onRecipeListResponse, this::onRecipeListResponse)
        observe(recipeViewModel.recipesVMDelegate.onRecipeListFilterResponse, this::onRecipeListFilterResponse)
    }

    //---- Initialize your ui here

    override fun init() {
        initSearch()
        initRecipesList()
        setOnClickListeners()
        recipeViewModel.getListOfRecipes()
    }

    //----- Logic Methods

    private fun setOnClickListeners() {
        bindingView.imgClear.setOnClickListener {
            bindingView.edtSearch.text.clear()
        }
    }

    //----- UI Methods

    private fun initSearch() {
        bindingView.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                if(s.toString().length > FILTER_CHARACTER_SIZE) {
                    bindingView.imgSearch.toGone()
                    bindingView.imgClear.toVisible()
                    recipeViewModel.filterRecipeBy(s.toString())
                }else{
                    if(s.toString().isEmpty()) {
                        bindingView.imgClear.toGone()
                        bindingView.imgSearch.toVisible()
                        setRecipeListData(recipeViewModel.recipeList.results)
                    }
                }
            }
        })
    }

    private fun initRecipesList() {
        val tmpAdapter = RecipesAdapter(onItemClickListener = recipesClickListener)
        bindingView.listOfRecipes.adapter = tmpAdapter
        bindingView.listOfRecipes.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun onRecipeListResponse(data: RecipeDataResponse) {
        setRecipeListData(data.results)
    }

    private fun onRecipeListFilterResponse(data: ArrayList<RecipeData>) {
        setRecipeListData(data)
    }

    private fun setRecipeListData(data: ArrayList<RecipeData>) {
        val tmpAdapter = RecipesAdapter(onItemClickListener = recipesClickListener)
        tmpAdapter.listData = data
        bindingView.listOfRecipes.adapter = tmpAdapter
    }

    private val recipesClickListener = object: IOnItemClickViewHolder {
        override fun onItemClick(caller: View?, position: Int) {
            val data = (bindingView.listOfRecipes.adapter as RecipesAdapter).listData[position]
            communication.onFragmentEvent(ProtocolAction.OnGoToRecipeDetail(data))
        }
    }

}