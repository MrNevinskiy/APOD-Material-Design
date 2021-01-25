package com.hw.apodmaterialdesign.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hw.apodmaterialdesign.R
import com.hw.apodmaterialdesign.util.ThemeHolder
import com.hw.apodmaterialdesign.view.fragment.APODFragment
import com.hw.apodmaterialdesign.view.fragment.MarsFragment
import com.hw.apodmaterialdesign.view.fragment.SettingsFragment
import com.hw.apodmaterialdesign.view.fragment.SolarFlareFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(ThemeHolder.id)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_api_bottom_container, APODFragment())
            .commitAllowingStateLoss()
        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, APODFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_mars -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, MarsFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_solar_flare -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, SolarFlareFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_setting -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, SettingsFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, APODFragment())
                        .commitAllowingStateLoss()
                    true
                }
            }
        }
    }
}