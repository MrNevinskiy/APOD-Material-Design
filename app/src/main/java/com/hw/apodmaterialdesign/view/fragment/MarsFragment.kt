package com.hw.apodmaterialdesign.view.fragment

import android.os.Bundle
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
import com.hw.apodmaterialdesign.util.ViewPagerAdapterMars
import com.hw.apodmaterialdesign.viewmodel.MarsFragmentViewModel
import kotlinx.android.synthetic.main.fragment_mars_view_pager.*

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
        return inflater.inflate(R.layout.fragment_mars_view_pager, container, false)
    }


    private fun renderData(data: MarsData?) {
        when (data) {
            is MarsData.Success -> {
                val earthDate: ArrayList<String> = ArrayList()
                val imageUrl: ArrayList<String> = ArrayList()
                val explanation: ArrayList<String> = ArrayList()
                val mediaType: ArrayList<String> = ArrayList()
                data.serverResponseData?.forEach {
                    it.hdurl?.let { img -> imageUrl.add(img)}
                    it.date?.let { date -> earthDate.add(date) }
                    it.explanation?.let { exp -> explanation.add(exp) }
                    it.mediaType?.let { mt -> mediaType.add(mt) }
                }
                if (imageUrl.isNullOrEmpty()) {
                    toast("Link is empty")
                } else {
                    view_pager.adapter = ViewPagerAdapterMars(childFragmentManager, earthDate, imageUrl, explanation, mediaType)
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

