package com.hw.apodmaterialdesign.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hw.apodmaterialdesign.R
import com.hw.apodmaterialdesign.ui.fragment.APODFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, APODFragment.newInstance())
                .commitNow()
        }
    }
}