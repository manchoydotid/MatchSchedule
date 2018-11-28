package com.example.brownbox.matchschedule.view.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brownbox.matchschedule.R
import kotlinx.android.synthetic.main.fragment_favorite_main.*


class FavoriteMainFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewpager_fav_main.adapter =
                FavoritePagerAdapter(childFragmentManager)
        tabs_fav_main.setupWithViewPager(viewpager_fav_main)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_main, container, false)
    }

}
