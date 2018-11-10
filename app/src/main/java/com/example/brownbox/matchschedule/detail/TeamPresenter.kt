package com.example.brownbox.matchschedule.detail

import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter(private val view: TeamDetailView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson
) {
    fun getDetailTeam(teamIdA: String?, teamIdB: String?) {
        view.showLoading()

        doAsync {
            val dataA = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamIdA)),
                TeamDetailItemResponse::class.java
            )
            val dataB = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamIdB)),
                TeamDetailItemResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamDetailList(dataA.teams, dataB.teams)
            }
        }

    }
}