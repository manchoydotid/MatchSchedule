package com.example.brownbox.matchschedule.teams.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TeamDetailPagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm){


    private val pages = listOf(
        TeamDescFragment(),
        TeamPlayerFragment()
    )
    override fun getItem(p0: Int): Fragment {
        return pages[p0] as Fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Description"
            else -> "Player"
        }
    }

}