package com.hw.apodmaterialdesign.view.fragment

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import coil.api.load
import com.hw.apodmaterialdesign.R
import kotlinx.android.synthetic.main.fragment_apod.*
import kotlinx.android.synthetic.main.fragment_apod.main
import kotlinx.android.synthetic.main.fragment_mars.*
import kotlinx.android.synthetic.main.fragment_mars_view_pager.*

class MarsPictureFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageZoomClickListener()

        val arguments = arguments
        arguments?.let { setImage(it.getString("imageUrl", ""),it.getString("mediaType", "")) }
        arguments?.let { setText(it.getString("date", "")) }
        arguments?.let { setExp(it.getString("exp", "")) }
    }

    private fun setImage(url: String, mediaType: String) {
        if (mediaType == "video") {
            mars_webView.clearCache(true)
            mars_webView.clearHistory()
            mars_webView.settings.javaScriptCanOpenWindowsAutomatically
            mars_webView.settings.javaScriptEnabled
            mars_webView.loadUrl(url)
        } else {
            image_view_mars.load(url) {
                lifecycle(this@MarsPictureFragment)
                error(R.drawable.ic_load_error_vector)
                placeholder(R.drawable.ic_no_photo_vector)
            }
        }
    }

    private fun setText(text: String) {
        text_view_mars.text = text
    }

    private fun setExp(text: String) {
        text_view_mars_exp.text = text
    }

    private fun imageZoomClickListener() {
        var isExpanded = false
        image_view_mars.setOnClickListener {
            isExpanded = !isExpanded
            TransitionManager.beginDelayedTransition(
                mars_ll, TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
            )

            val params: ViewGroup.LayoutParams = image_view_mars.layoutParams
            params.height =
                if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT
                else ViewGroup.LayoutParams.WRAP_CONTENT
            image_view_mars.layoutParams = params
            image_view_mars.scaleType =
                if (isExpanded) ImageView.ScaleType.CENTER_CROP
                else ImageView.ScaleType.FIT_CENTER
        }
    }

}