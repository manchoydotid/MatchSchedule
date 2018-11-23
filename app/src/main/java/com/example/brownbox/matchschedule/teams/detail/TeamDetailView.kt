package com.example.brownbox.matchschedule.teams.detail

import com.example.brownbox.matchschedule.detail.TeamDetailItem

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<TeamDetailItem>)
}