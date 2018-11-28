package com.example.brownbox.matchschedule.view.teamDetail

import com.example.brownbox.matchschedule.model.DetailTeamModel.TeamDetailItem

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<TeamDetailItem>)
}