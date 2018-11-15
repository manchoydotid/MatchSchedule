package com.example.brownbox.matchschedule.detail

import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: LeagueDetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson
) {
    fun getDetailEvent(eventId: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getLeagueDetail(eventId)).await(),
                LeagueDetailItemResponse::class.java
            )

                view.showLeagueDetailList(data.events)
                view.hideLoading()
        }
    }
}