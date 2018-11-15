package com.example.brownbox.matchschedule

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.brownbox.matchschedule.favorite.FavoritesFragment

class PagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm){


    private val pages = listOf(
        LastFragment(),
        NextFragment(),
        FavoritesFragment()
    )
    override fun getItem(p0: Int): Fragment {
        return pages[p0] as Fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Last Match"
            1 -> "Next Match"
            else -> "Favorites"
        }
    }

}