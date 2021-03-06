package com.example.brownbox.matchschedule.presenter

import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.example.brownbox.matchschedule.model.TeamModel.TeamResponse
import com.example.brownbox.matchschedule.util.CoroutineContextProvider
import com.example.brownbox.matchschedule.view.team.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson, private val context:
                     CoroutineContextProvider = CoroutineContextProvider())
{
    fun getTeamList(league: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)).await(),
                TeamResponse::class.java
            )
            view.showTeamList(data.teams)
            view.hideLoading()
        }

    }

    fun getTeamSearch(keyword: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamSearch(keyword)).await(),
                TeamResponse::class.java
            )
            try{
                view.showTeamList(data.teams)
            }catch (e:Exception){
                view.emptyState()
            }
            view.hideLoading()
        }

    }

}