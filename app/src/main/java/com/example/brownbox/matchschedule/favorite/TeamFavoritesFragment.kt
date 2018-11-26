package com.example.brownbox.matchschedule.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.database
import com.example.brownbox.matchschedule.teamDatabase
import com.example.brownbox.matchschedule.teams.detail.TeamDetailActivity
import kotlinx.android.synthetic.main.fragment_team_favorites.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh


class TeamFavoritesFragment : Fragment() {

    private val favorites: MutableList<TeamFavorites> = mutableListOf()
    private lateinit var adapter: TeamFavoritesAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = TeamFavoritesAdapter(favorites){
            context?.startActivity<TeamDetailActivity>(
                "idTeam" to "${it.idTeam}")
        }

        team_fav_rv.adapter = adapter
        team_fav_rv.layoutManager = LinearLayoutManager(requireContext())
        favorites.clear()
        showFavorite()

        team_fav_swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }
    

    private fun showFavorite(){
        context?.teamDatabase?.use{
            team_fav_swipeRefresh.isRefreshing = false
            val result = select(TeamFavorites.TABLE_TEAM_FAVORITE)
            val favorite = result.parseList(classParser<TeamFavorites>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_favorites, container, false)
    }


}
