package com.hw.apodmaterialdesign.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.api.load
import com.hw.apodmaterialdesign.R
import kotlinx.android.synthetic.main.fragment_mars.*

class MarsPictureFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments =  arguments
        arguments?.let {setImage(it.getString("imageUrl", ""))}
        arguments?.let {setText(it.getString("date", "")) }
    }

    fun setImage(url:String){
        image_view_mars.load(url) {
            lifecycle(this@MarsPictureFragment)
            error(R.drawable.ic_load_error_vector)
            placeholder(R.drawable.ic_no_photo_vector)
        }
    }

    fun setText(text: String){
        text_view_mars.text = text
    }

}