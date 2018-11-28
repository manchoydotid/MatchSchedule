package com.example.brownbox.matchschedule.view.teamDetail

import com.example.brownbox.matchschedule.model.DetailTeamModel.TeamDetailItem

interface TeamDetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetailList(dataA: List<TeamDetailItem>, dataB: List<TeamDetailItem>)
}