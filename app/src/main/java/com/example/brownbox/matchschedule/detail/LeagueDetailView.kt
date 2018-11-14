package com.example.brownbox.matchschedule.detail

import com.example.brownbox.matchschedule.detail.LeagueDetailItem

interface LeagueDetailView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueDetailList(data: List<LeagueDetailItem>)
}