package com.example.brownbox.matchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.brownbox.matchschedule.view.main.LastFragment
import com.example.brownbox.matchschedule.view.main.NextFragment

class MatchPagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm){


    private val pages = listOf(
        NextFragment(),
        LastFragment()
    )
    override fun getItem(p0: Int): Fragment {
        return pages[p0] as Fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Next Match"
            else -> "Last Match"
        }
    }

}