package com.example.brownbox.matchschedule.view.teamDetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.model.DetailTeamModel.TeamDetailItem
import com.example.brownbox.matchschedule.adapter.TeamDetailPagerAdapter.Companion.KEY_TEAM
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team_desc.*


class TeamDescFragment : Fragment(), TeamDetailView {


    private lateinit var teamDetailItem: TeamDetailItem
    private lateinit var presenter: TeamDetailPresenter
    private var teamId: String =""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bindData = arguments
        teamId = bindData?.getString(KEY_TEAM)?: "idTeam"

        val request = ApiRepository()
        val gson = Gson()

        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getDetailTeam(teamId)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_desc, container, false)
    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamDetail(data: List<TeamDetailItem>) {
        teamDetailItem = TeamDetailItem(
            data[0].idTeam,
            data[0].strTeam,
            data[0].strTeamBadge,
            data[0].intFormedYear,
            data[0].strStadium,
            data[0].strDescriptionEN
        )

        detail_team_desc.text = data[0].strDescriptionEN

    }
}
