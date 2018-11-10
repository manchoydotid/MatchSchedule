package com.example.brownbox.matchschedule.detail

import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: LeagueDetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson
) {
    fun getDetailEvent(eventId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getLeagueDetail(eventId)),
                LeagueDetailItemResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showLeagueDetailList(data.events)
            }
        }
    }
}