package com.example.brownbox.matchschedule

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.brownbox.matchschedule.favorite.MatchFavoritesFragment
import com.example.brownbox.matchschedule.favorite.TeamFavoritesFragment

class FavoritePagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
        MatchFavoritesFragment(),
        TeamFavoritesFragment()
    )

    override fun getItem(p0: Int): Fragment {
        return pages[p0] as Fragment
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