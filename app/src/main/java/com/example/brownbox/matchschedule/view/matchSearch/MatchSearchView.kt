package com.example.brownbox.matchschedule.view.matchSearch

import com.example.brownbox.matchschedule.model.EventLeagueModel.LeagueItem

interface MatchSearchView{
    fun showLoading()
    fun hideLoading()
    fun emptyState()
    fun showEventList(data: List<LeagueItem>)
}