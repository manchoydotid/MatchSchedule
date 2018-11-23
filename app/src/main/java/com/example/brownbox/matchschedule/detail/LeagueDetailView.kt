package com.example.brownbox.matchschedule.detail

interface LeagueDetailView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueDetailList(data: List<LeagueDetailItem>)
}