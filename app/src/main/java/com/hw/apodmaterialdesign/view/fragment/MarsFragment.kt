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
import com.hw.apodmaterialdesign.R
import com.hw.apodmaterialdesign.model.MarsData
import com.hw.apodmaterialdesign.model.entity.mars.Rover
import com.hw.apodmaterialdesign.util.ViewPagerAdapter
import com.hw.apodmaterialdesign.viewmodel.MarsFragmentViewModel
import kotlinx.android.synthetic.main.view_pager_mars.*
import java.util.*
import kotlin.collections.ArrayList

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
        return inflater.inflate(R.layout.view_pager_mars, container, false)
    }


    private fun renderData(data: MarsData?) {
        when (data) {
            is MarsData.Success -> {
                val earthDate: ArrayList<String> = ArrayList()
                val imageUrl: ArrayList<String> = ArrayList()
                data.serverResponseData.photos?.forEach {
                    it.img_src?.let { img -> imageUrl.add(img)}
                    it.earth_date?.let { date -> earthDate.add(date) }
                }
                if (imageUrl.isNullOrEmpty()) {
                    toast("Link is empty")
                } else {
                    view_pager.adapter = ViewPagerAdapter(childFragmentManager, earthDate, imageUrl)
                    tab_layout.setupWithViewPager(view_pager)
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

