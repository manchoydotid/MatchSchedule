package com.example.brownbox.matchschedule.teams.detail.players.detail

import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.example.brownbox.matchschedule.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerDetailPresenter( private val playerDetailView: PlayerDetailView,
                             private val apiRepository: ApiRepository,
                             private val gson:Gson, private val context:
                             CoroutineContextProvider = CoroutineContextProvider()
)
{
    fun getPlayerDetail(playerId: String?){
        playerDetailView.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getPlayerDetail(playerId)).await(),
                PlayerDetailItemResponse::class.java
            )
            playerDetailView.showPlayerDetail(data.players)
            playerDetailView.hideLoading()
        }
    }
}