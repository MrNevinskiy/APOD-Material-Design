package com.hw.apodmaterialdesign.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.hw.apodmaterialdesign.R
import com.hw.apodmaterialdesign.model.MarsData
import com.hw.apodmaterialdesign.viewmodel.MarsFragmentViewModel
import kotlinx.android.synthetic.main.fragment_apod.*
import kotlinx.android.synthetic.main.fragment_mars.*

class MarsFragment : Fragment() {

    private val viewModel: MarsFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(MarsFragmentViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer<MarsData> { renderData(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mars, container, false)
    }


    private fun renderData(data: MarsData?) {
        when (data) {
            is MarsData.Success -> {
                val earthDate: ArrayList<String> = ArrayList()
                val imageUrl: ArrayList<String> = ArrayList()
                data.serverResponseData.photos?.forEach {
                    it.img_src?.let { img -> imageUrl.add(img) }
                    it.earth_date?.let { date -> earthDate.add(date) }
                }
                if (imageUrl.isNullOrEmpty()) {
                    toast("Link is empty")
                } else {
                    image_view_mars.load(imageUrl[0]) {
                        lifecycle(this@MarsFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                    }
                }
            }
            is MarsData.Loading -> {

            }
            is MarsData.Error -> {
                toast(data.error.message)
            }
        }
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_LONG).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }
}

