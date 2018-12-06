package com.example.brownbox.matchschedule.view.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.db.database
import com.example.brownbox.matchschedule.favorite.FavoritesEventAdapter
import com.example.brownbox.matchschedule.favorite.MatchFavorites
import com.example.brownbox.matchschedule.view.matchDetail.DetailMatchActivity
import kotlinx.android.synthetic.main.fragment_match_favorites.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


class MatchFavoritesFragment : Fragment() {

    private var favorites: MutableList<MatchFavorites> = mutableListOf()
    private lateinit var adapter: FavoritesEventAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoritesEventAdapter(requireContext(), favorites) {
            startActivity<DetailMatchActivity>(
                "idEvent" to "${it.idEvent}",
                "idHome" to "${it.idHomeTeam}",
                "idAway" to "${it.idAwayTeam}"
            )
        }

        rv_fav_fragment.adapter = adapter
        rv_fav_fragment.layoutManager = LinearLayoutManager(requireContext())
        favorites.clear()
        showFavorites()

        fav_swipeRefresh.onRefresh {
            favorites.clear()
            showFavorites()
        }

    }

    private fun showFavorites(){
        context?.database?.use{
            fav_swipeRefresh.isRefreshing = false
            val result = select(MatchFavorites.TABLE_FAVORITES)
            val favorite = result.parseList(classParser<MatchFavorites>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_favorites, container, false)
    }


}
