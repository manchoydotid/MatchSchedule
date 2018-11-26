package com.example.brownbox.matchschedule.teams.detail.players.detail

interface PlayerDetailView{
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: List<PlayerDetailItem>)
}