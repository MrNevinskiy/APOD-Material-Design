package com.hw.apodmaterialdesign.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hw.apodmaterialdesign.view.fragment.MarsPictureFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val date: ArrayList<String>,
    private val imageUrl: ArrayList<String>,
    private val exp: ArrayList<String>,
    private val mediaType: ArrayList<String>
) :
    FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        val arguments = Bundle()
        arguments.putString("date", date[position])
        arguments.putString("imageUrl", imageUrl[position])
        arguments.putString("exp", exp[position])
        arguments.putString("mediaType", mediaType[position])

        val fragment = MarsPictureFragment()
        fragment.arguments = arguments

        return fragment
    }

    override fun getCount(): Int {
        return date.size
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return date[position]
    }

}