package com.recipess.app.features.recipes.view

import android.net.Uri
import android.widget.MediaController
import androidx.navigation.fragment.navArgs
import com.adrenastudies.trufisapp.core.base.BaseFragment
import com.recipess.app.databinding.LayoutRecipesVideoViewFragmentBinding

class RecipeVideoViewFragment  : BaseFragment<LayoutRecipesVideoViewFragmentBinding>() {

    private val args: RecipeVideoViewFragmentArgs by navArgs()

    //---- Base functions
    override fun screenName() = "RecipeVideoViewFragment"

    override fun listenToObserver() {}

    //---- Initialize your ui here

    override fun init() {
        initVideo()
        setOnClickListeners()
    }

    override fun onStop() {
        super.onStop()
        bindingView.videoView.stopPlayback()
    }

    override fun onPause() {
        super.onPause()
        bindingView.videoView.stopPlayback()
    }

    //----- Logic Methods

    private fun setOnClickListeners() {

    }

    private fun initVideo() {
        val controller = MediaController(requireContext())
        val videoUri = Uri.parse(args.url)
        controller.setAnchorView(bindingView.videoView)
        bindingView.videoView.setMediaController(controller)
        bindingView.videoView.setVideoURI(videoUri)
        bindingView.videoView.start()
    }

    //----- UI Methods

}