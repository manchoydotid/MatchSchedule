package com.example.brownbox.matchschedule.view.team

import com.example.brownbox.matchschedule.model.TeamModel.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun emptyState()
    fun showTeamList(data: List<Team>)
}