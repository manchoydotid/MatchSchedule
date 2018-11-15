package com.example.brownbox.matchschedule.main

import android.provider.Settings
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.example.brownbox.matchschedule.model.LeagueItemResponse
import com.example.brownbox.matchschedule.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson, private val context:
                    CoroutineContextProvider = CoroutineContextProvider())
{
    fun getPastLeagueList(league: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPastLeague(league)).await(),
                LeagueItemResponse::class.java
            )

                view.showEventList(data.events)
                view.hideLoading()
        }
    }

    fun getNextLeagueList(league: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextLeague(league)).await(),
                LeagueItemResponse::class.java
            )

                view.showEventList(data.events)
                view.hideLoading()
        }
    }


}