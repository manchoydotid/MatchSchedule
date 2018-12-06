package com.example.brownbox.matchschedule.presenter

import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.example.brownbox.matchschedule.model.EventLeagueModel.LeagueItemResponse
import com.example.brownbox.matchschedule.util.CoroutineContextProvider
import com.example.brownbox.matchschedule.view.main.MainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson, private val context:
                    CoroutineContextProvider = CoroutineContextProvider())
{
    fun getPastLeagueList(leagueName: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPastLeague(leagueName)).await(),
                LeagueItemResponse::class.java
            )

                view.showEventList(data.events)
                view.hideLoading()
        }
    }

    fun getNextLeagueList(leagueName: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextLeague(leagueName)).await(),
                LeagueItemResponse::class.java
            )

                view.showEventList(data.events)
                view.hideLoading()
        }
    }


}