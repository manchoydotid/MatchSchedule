package com.example.brownbox.matchschedule.main

import com.example.brownbox.matchschedule.model.LeagueItem

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<LeagueItem>)
}