package com.example.brownbox.matchschedule.detail

import com.example.brownbox.matchschedule.detail.TeamDetailItem

interface TeamDetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetailList(dataA: List<TeamDetailItem>, dataB: List<TeamDetailItem>)
}