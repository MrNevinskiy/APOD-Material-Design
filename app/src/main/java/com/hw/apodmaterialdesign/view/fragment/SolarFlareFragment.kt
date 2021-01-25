package com.hw.apodmaterialdesign.view.fragment

import android.content.Intent
import android.net.Uri
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
import com.hw.apodmaterialdesign.model.SolarFlareData
import com.hw.apodmaterialdesign.viewmodel.SolarFlareFragmentViewModel
import kotlinx.android.synthetic.main.fragment_solar_flare.*

class SolarFlareFragment : Fragment() {

    private lateinit var link: String

    private val viewModel: SolarFlareFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(SolarFlareFragmentViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer<SolarFlareData> { renderData(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_solar_flare, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_link.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse(link)
            })
        }
    }

    private fun renderData(data: SolarFlareData?) {
        when (data) {
            is SolarFlareData.Success -> {
                lateinit var startTime: String
                lateinit var endTime: String
                data.serverResponseData.forEach{
                    it.beginTime?.let { bt -> startTime = bt }
                    it.endTime?.let { et -> endTime = et }
                    it.link?.let { li -> link = li }
                }

                if (data.serverResponseData.isEmpty()) {
                    toast("Link is empty")
                } else {
                    tv_startTime.text = startTime
                    tv_endTime.text = endTime
                    tv_link.text = link
                }
            }
            is SolarFlareData.Loading -> {

            }
            is SolarFlareData.Error -> {
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