package com.example.brownbox.matchschedule.view.player

import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.example.brownbox.matchschedule.model.TeamPlayerModel.TeamPlayerItemResponse
import com.example.brownbox.matchschedule.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPlayerPresenter(private val teamPlayerView: TeamPlayerView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson, private val context:
                            CoroutineContextProvider = CoroutineContextProvider()
)
{
    fun getTeamPlayer(teamId: String?){
        teamPlayerView.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getPlayers(teamId)).await(),
                TeamPlayerItemResponse::class.java
            )

            teamPlayerView.showTeamPlayer(data.player)
            teamPlayerView.hideLoading()
        }
    }
}