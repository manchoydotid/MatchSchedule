package com.example.brownbox.matchschedule.view.favorite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FavoritePagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
        MatchFavoritesFragment(),
        TeamFavoritesFragment()
    )

    override fun getItem(p0: Int): Fragment {
        return pages[p0]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Favorite Match"
            else -> "Favorite Team"
        }
    }
}