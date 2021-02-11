package com.hw.apodmaterialdesign.view.fragment

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.LeadingMarginSpan
import android.text.style.URLSpan
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.hw.apodmaterialdesign.R
import com.hw.apodmaterialdesign.model.PictureOfTheDayData
import com.hw.apodmaterialdesign.viewmodel.APODFragmentViewModel
import kotlinx.android.synthetic.main.fragment_apod.*

class APODFragment : Fragment() {

    private val viewModel: APODFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(APODFragmentViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData()
            .observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_apod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            text_view.typeface = Typeface.createFromAsset(it.assets, "RobotoLightItalic-E9nn.ttf")
        }
        input_layout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${input_edit_text.text.toString()}")
            })
        }
    }

    private fun renderData(data: PictureOfTheDayData?) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    toast("Link is empty")
                } else {
                    setDescription(serverResponseData.explanation)
                    if (serverResponseData.mediaType == "video") {
                        webView.clearCache(true)
                        webView.clearHistory()
                        webView.settings.javaScriptCanOpenWindowsAutomatically
                        webView.settings.javaScriptEnabled
                        webView.loadUrl(url)
                    } else {
                        image_view.load(url) {
                            lifecycle(viewLifecycleOwner)
                            error(R.drawable.ic_load_error_vector)
                            placeholder(R.drawable.ic_no_photo_vector)
                        }
                    }
                }
            }
            is PictureOfTheDayData.Loading -> {

            }
            is PictureOfTheDayData.Error -> {
                toast(data.error.message)
            }
        }
    }

    private fun setDescription(description: String?) {
        description?.let {
            val spannableString = SpannableString(it)
            spannableString.setSpan(
                LeadingMarginSpan.Standard(100, 0),
                0,
                it.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
            text_view.text = spannableString
        }
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

}
