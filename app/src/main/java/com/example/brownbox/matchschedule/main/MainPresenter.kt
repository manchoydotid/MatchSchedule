package com.example.brownbox.matchschedule.main

import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.example.brownbox.matchschedule.model.LeagueItemResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson
) {
    fun getPastLeagueList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPastLeague(league)),
                LeagueItemResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }

    fun getNextLeagueList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextLeague(league)),
                LeagueItemResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }


}