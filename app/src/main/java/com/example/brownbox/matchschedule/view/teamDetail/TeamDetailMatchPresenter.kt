package com.example.brownbox.matchschedule.view.teamDetail

import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.example.brownbox.matchschedule.model.DetailTeamModel.TeamDetailItemResponse
import com.example.brownbox.matchschedule.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailMatchPresenter(private val matchView: TeamDetailMatchView,
                               private val apiRepository: ApiRepository,
                               private val gson: Gson, private val context:
                    CoroutineContextProvider = CoroutineContextProvider())
{
    fun getDetailTeam(teamIdA: String?, teamIdB: String?) {
        matchView.showLoading()

        GlobalScope.launch(context.main) {
            val dataA = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamIdA)).await(),
                TeamDetailItemResponse::class.java
            )
            val dataB = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamIdB)).await(),
                TeamDetailItemResponse::class.java
            )


                matchView.showTeamDetailList(dataA.teams, dataB.teams)
                matchView.hideLoading()
        }

    }
}












