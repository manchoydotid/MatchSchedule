package com.example.brownbox.matchschedule.teams.detail.players

interface TeamPlayerView{
    fun showLoading()
    fun hideLoading()
    fun showTeamPlayer(data: List<TeamPlayerItem>)

}