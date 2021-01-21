package com.hw.apodmaterialdesign.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hw.apodmaterialdesign.R
import com.hw.apodmaterialdesign.util.ThemeHolder
import com.hw.apodmaterialdesign.view.fragment.APODFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(ThemeHolder.id)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, APODFragment.newInstance())
                .commitNow()
        }
    }
}