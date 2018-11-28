package com.example.brownbox.matchschedule.view.main

import com.example.brownbox.matchschedule.model.EventLeagueModel.LeagueItem

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<LeagueItem>)
}