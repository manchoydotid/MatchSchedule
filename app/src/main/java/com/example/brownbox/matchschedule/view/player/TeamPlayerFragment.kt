package com.example.brownbox.matchschedule.view.player


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brownbox.matchschedule.R

import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.model.TeamPlayerModel.TeamPlayerItem
import com.example.brownbox.matchschedule.adapter.TeamDetailPagerAdapter.Companion.KEY_TEAM_2
import com.example.brownbox.matchschedule.adapter.TeamPlayerAdapter
import com.example.brownbox.matchschedule.presenter.TeamPlayerPresenter
import com.example.brownbox.matchschedule.view.playerDetail.PlayerDetailActivity
import com.google.gson.Gson
import org.jetbrains.anko.startActivity


class TeamPlayerFragment : Fragment(), TeamPlayerView {

    private lateinit var listPlayer: RecyclerView
    private lateinit var presenter: TeamPlayerPresenter
    private lateinit var adapter: TeamPlayerAdapter
    private var players: MutableList<TeamPlayerItem> = mutableListOf()
    private var teamId: String = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val binData = arguments
        teamId = binData?.getString(KEY_TEAM_2)?: "idTeam"

        adapter = TeamPlayerAdapter(players) {
            context?.startActivity<PlayerDetailActivity>(
                "idPlayer" to "${it.idPlayer}"
            )
        }

        listPlayer = view?.findViewById(R.id.detail_players_rv) as RecyclerView
        listPlayer.layoutManager = LinearLayoutManager(requireContext())
        listPlayer.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()

        presenter = TeamPlayerPresenter(this, request, gson)
        presenter.getTeamPlayer(teamId)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_player, container, false)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamPlayer(data: List<TeamPlayerItem>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()

    }


}
