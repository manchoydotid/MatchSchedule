package com.example.brownbox.matchschedule.detail


data class LeagueDetailItem(
    var idEvent: String? = null ,
    var dateEvent: String? = null,

    var strHomeTeam: String? = null,
    var idHomeTeam: String? = null,
    var intHomeScore: String? = null,
    var strHomeFormation: String? = null,
    var intHomeShots: String? = null,
    var strHomeGoalDetails: String? = null,
    var strHomeLineupGoalkeeper: String? = null,
    var strHomeLineupDefense: String? = null,
    var strHomeLineupMidfield: String? = null,
    var strHomeLineupForward: String? = null,
    var strHomeLineupSubstitutes: String? = null,

    var strAwayTeam: String? = null,
    var idAwayTeam: String? = null,
    var intAwayScore: String? = null,
    var strAwayFormation: String? = null,
    var intAwayShots: String? = null,
    var strAwayGoalDetails: String? = null,
    var strAwayLineupGoalkeeper: String? = null,
    var strAwayLineupDefense: String? = null,
    var strAwayLineupMidfield: String? = null,
    var strAwayLineupForward: String? = null,
    var strAwayLineupSubstitutes: String?


)