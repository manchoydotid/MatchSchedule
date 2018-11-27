package com.example.brownbox.matchschedule.teams

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun emptyState()
    fun showTeamList(data: List<Team>)
}