package com.example.brownbox.matchschedule.view.main

import com.example.brownbox.matchschedule.model.DetailEventModel.LeagueDetailItem

interface LeagueDetailView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueDetailList(data: List<LeagueDetailItem>)
}