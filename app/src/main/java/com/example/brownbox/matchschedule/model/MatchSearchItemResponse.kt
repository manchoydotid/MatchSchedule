package com.example.brownbox.matchschedule.model

import com.example.brownbox.matchschedule.model.EventLeagueModel.LeagueItem

data class MatchSearchItemResponse(
    val event: List<LeagueItem>
)