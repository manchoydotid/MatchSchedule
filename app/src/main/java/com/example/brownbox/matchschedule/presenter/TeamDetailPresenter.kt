package com.example.brownbox.matchschedule.presenter

import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.example.brownbox.matchschedule.model.DetailTeamModel.TeamDetailItemResponse
import com.example.brownbox.matchschedule.util.CoroutineContextProvider
import com.example.brownbox.matchschedule.view.teamDetail.TeamDetailView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter(private val teamView: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson, private val context:
                               CoroutineContextProvider = CoroutineContextProvider()
)
{
    fun getDetailTeam(teamId: String?) {
        teamView.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                TeamDetailItemResponse::class.java
            )

            teamView.showTeamDetail(data.teams)
            teamView.hideLoading()
        }

    }
}