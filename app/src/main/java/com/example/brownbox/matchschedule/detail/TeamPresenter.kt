package com.example.brownbox.matchschedule.detail

import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.example.brownbox.matchschedule.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter(private val view: TeamDetailView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson,private val context:
                    CoroutineContextProvider = CoroutineContextProvider())
{
    fun getDetailTeam(teamIdA: String?, teamIdB: String?) {
        view.showLoading()

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


                view.showTeamDetailList(dataA.teams, dataB.teams)
                view.hideLoading()
        }

    }
}












