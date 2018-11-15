package com.example.brownbox.matchschedule.main

import com.example.brownbox.matchschedule.TestContextProvider
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.example.brownbox.matchschedule.model.LeagueItem
import com.example.brownbox.matchschedule.model.LeagueItemResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock
    private
    lateinit var view: MainView

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var mainPresenter: MainPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        mainPresenter = MainPresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun getPastLeagueList() {
        val events: MutableList<LeagueItem> = mutableListOf()
        val response = LeagueItemResponse(events)
        val leagueId = "4328"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPastLeague(leagueId)).await(),
                LeagueItemResponse::class.java
            )).thenReturn(response)

            mainPresenter.getPastLeagueList(leagueId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventList(events)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getNextLeagueList() {
        val events: MutableList<LeagueItem> = mutableListOf()
        val response = LeagueItemResponse(events)
        val leagueId = "4328"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getNextLeague(leagueId)).await(),
                    LeagueItemResponse::class.java
                )
            ).thenReturn(response)

            mainPresenter.getNextLeagueList(leagueId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventList(events)
            Mockito.verify(view).hideLoading()
        }
    }
}