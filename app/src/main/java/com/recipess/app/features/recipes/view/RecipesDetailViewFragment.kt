package com.recipess.app.features.recipes.view

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.adrenastudies.trufisapp.core.base.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.recipess.app.R
import com.recipess.app.core.extensions.observe
import com.recipess.app.core.extensions.setImageSrcFromUrl
import com.recipess.app.core.extensions.toGone
import com.recipess.app.core.extensions.toVisible
import com.recipess.app.core.protocol.ProtocolAction
import com.recipess.app.databinding.LayoutRecipesDetailViewFragmentBinding
import com.recipess.app.features.recipes.datasource.model.RecipeData
import com.recipess.app.features.recipes.viewmodel.RecipesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt


class RecipesDetailViewFragment : BaseFragment<LayoutRecipesDetailViewFragmentBinding>(), OnMapReadyCallback {

    private val args: RecipesDetailViewFragmentArgs by navArgs()

    //---- Base functions
    override fun screenName() = "RecipesViewFragment"

    override fun listenToObserver() {}

    //---- Initialize your ui here

    override fun init() {
        initUI()
        setOnClickListeners()
    }

    //----- Logic Methods

    private fun setOnClickListeners() {
        bindingView.btnVideo.setOnClickListener {
            communication.onFragmentEvent(ProtocolAction.OnGoToRecipeVideo(args.recipeData.video_url))
        }
    }

    private fun initUI() {
        setRatings()
        setInstructionsList()
        bindingView.txtName.text = args.recipeData.name
        bindingView.txtDescription.text = args.recipeData.description
        bindingView.txtCookTime.text = getString(R.string.txt_recipe_detail_cook, args.recipeData.cook_time_minutes)
        bindingView.imgRecipe.setImageSrcFromUrl(args.recipeData.thumbnail_url, R.mipmap.ic_launcher, requireContext())
        bindingView.txtPreparationTime.text = getString(R.string.txt_recipe_detail_preparation, args.recipeData.prep_time_minutes)
    }

    private fun roundRating(num: Float): Float {
        val round =  (num * 10f).roundToInt() / 10f
        return round * 10
    }

    private fun setInstructionsList() {
        if(args.recipeData.instructions != null) {
            args.recipeData.instructions!!.forEach {
                val customView: View = LayoutInflater.from(context).inflate(R.layout.instructions_view_cell, null)
                val txtInstruction = customView.findViewById<TextView>(R.id.txtInstruction)
                txtInstruction.text = it.display_text
                bindingView.lyRecipeStep.addView(customView)
            }
        }else{
            bindingView.txtInstructions.toGone()
        }
    }

    //----- UI Methods

    override fun onMapReady(googleMap: GoogleMap) {
        val sydney = LatLng(-37.0526809, -81.644528)
        googleMap.addMarker(
            MarkerOptions().position(sydney)
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13f))
    }

    private fun setRatings() {
        val rating = args.recipeData.user_ratings
        if(rating != null) {
            bindingView.lyRatingPositive.toVisible()
            bindingView.lyRatingNegative.toVisible()
            bindingView.lyRatingScore.toVisible()

            bindingView.txtPositive.text = rating.count_positive.toString()
            bindingView.txtNegative.text = rating.count_negative.toString()
            bindingView.txtScore.text = roundRating(rating.score).toString()
        }else{
            bindingView.lyRatingPositive.toGone()
            bindingView.lyRatingNegative.toGone()
            bindingView.lyRatingScore.toGone()
        }
    }
}