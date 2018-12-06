package com.example.brownbox.matchschedule.presenter

import com.example.brownbox.matchschedule.TestContextProvider
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.example.brownbox.matchschedule.model.TeamPlayerModel.TeamPlayerItem
import com.example.brownbox.matchschedule.model.TeamPlayerModel.TeamPlayerItemResponse
import com.example.brownbox.matchschedule.view.player.TeamPlayerView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamPlayerPresenterTest {

    @Mock
    private
    lateinit var view: TeamPlayerView

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var teamPlayerPresenter: TeamPlayerPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        teamPlayerPresenter =
                TeamPlayerPresenter(
                    view,
                    apiRepository,
                    gson,
                    TestContextProvider()
                )
    }

    @Test
    fun getTeamEvent() {
        val player: MutableList<TeamPlayerItem> = mutableListOf()
        val response =
            TeamPlayerItemResponse(player)
        val teamId = "133604"

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getPlayers(teamId)).await(),
                    TeamPlayerItemResponse::class.java
                )
            ).thenReturn(response)

            teamPlayerPresenter.getTeamPlayer(teamId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamPlayer(player)
            Mockito.verify(view).hideLoading()
        }
    }

}