package com.example.brownbox.matchschedule.view.playerDetail

import com.example.brownbox.matchschedule.model.DetailPlayerModel.PlayerDetailItem

interface PlayerDetailView{
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: List<PlayerDetailItem>)
}