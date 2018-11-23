package com.example.brownbox.matchschedule.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.database
import com.example.brownbox.matchschedule.detail.DetailMatchActivity
import kotlinx.android.synthetic.main.fragment_favorites_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


class FavoritesFragment : Fragment() {

    private var favorites: MutableList<Favorites> = mutableListOf()
    private lateinit var adapter: FavoritesEventAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoritesEventAdapter(ctx, favorites) {
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
            val result = select(Favorites.TABLE_FAVORITES)
            val favorite = result.parseList(classParser<Favorites>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_match, container, false)
    }


}
