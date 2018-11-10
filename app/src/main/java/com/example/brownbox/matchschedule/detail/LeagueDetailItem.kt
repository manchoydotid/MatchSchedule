package com.example.brownbox.matchschedule.detail

data class LeagueDetailItem(
    val idEvent: String,
    val dateEvent: String,

    val strHomeTeam: String,
    val idHomeTeam: String,
    val intHomeScore: String,
    val intHomeShots: String,
    val strHomeGoalDetails: String,
    val strHomeLineupGoalkeeper: String,
    val strHomeLineupDefense: String,
    val strHomeLineupMidfield: String,
    val strHomeLineupForward: String,
    val strHomeLineupSubstitutes: String,

    val strAwayTeam: String,
    val idAwayTeam: String,
    val intAwayScore: String,
    val intAwayShots: String,
    val strAwayGoalDetails: String,
    val strAwayLineupGoalkeeper: String,
    val strAwayLineupDefense: String,
    val strAwayLineupMidfield: String,
    val strAwayLineupForward: String,
    val strAwayLineupSubstitutes: String


)