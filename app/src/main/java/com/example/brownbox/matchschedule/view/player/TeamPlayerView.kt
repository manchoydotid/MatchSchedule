package com.example.brownbox.matchschedule.view.player

import com.example.brownbox.matchschedule.model.TeamPlayerModel.TeamPlayerItem

interface TeamPlayerView{
    fun showLoading()
    fun hideLoading()
    fun showTeamPlayer(data: List<TeamPlayerItem>)

}