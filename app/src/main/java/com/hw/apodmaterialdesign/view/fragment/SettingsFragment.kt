package com.hw.apodmaterialdesign.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hw.apodmaterialdesign.R
import com.hw.apodmaterialdesign.util.ThemeHolder
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ThemeHolder.id == R.style.Theme_APODMaterialDesignDark){
            selected_theme.isChecked = true
        }
        selected_theme.setOnClickListener {
            if (selected_theme.isChecked) {
                ThemeHolder.id = R.style.Theme_APODMaterialDesignDark
            } else {
                ThemeHolder.id = R.style.Theme_APODMaterialDesign
            }
            activity?.recreate()
        }
    }
}