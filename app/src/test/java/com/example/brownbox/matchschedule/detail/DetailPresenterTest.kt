package com.example.brownbox.matchschedule.detail

import com.example.brownbox.matchschedule.TestContextProvider
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DetailPresenterTest {

    @Mock
    private
    lateinit var view: LeagueDetailView

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var detailPresenter: DetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailPresenter = DetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getDetailEvent() {
        val league: MutableList<LeagueDetailItem> = mutableListOf()
        val response = LeagueDetailItemResponse(league)
        val eventId = "576577"

        GlobalScope.launch {
            `when` (gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLeagueDetail(eventId)).await(),
                LeagueDetailItemResponse::class.java
            )).thenReturn(response)

            detailPresenter.getDetailEvent(eventId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLeagueDetailList(league)
            Mockito.verify(view).hideLoading()
        }
    }
}












