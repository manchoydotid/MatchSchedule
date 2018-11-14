package com.example.brownbox.matchschedule


data class Favorites(
    val id: Long?,
    val idEvent: String?,
    val dateEvent: String?,

    val strHomeTeam: String?,
    val idHomeTeam: String?,
    val intHomeScore: String?,
//    val strHomeFormation: String? = null,
//    val intHomeShots: String? = null,
//    val strHomeGoalDetails: String? = null,
//    val strHomeLineupGoalkeeper: String? = null,
//    val strHomeLineupDefense: String? = null,
//    val strHomeLineupMidfield: String? = null,
//    val strHomeLineupForward: String? = null,
//    val strHomeLineupSubstitutes: String? = null,

    val strAwayTeam: String?,
    val idAwayTeam: String?,
    val intAwayScore: String?
//    val strAwayFormation: String? = null,
//    val intAwayShots: String? = null,
//    val strAwayGoalDetails: String? = null,
//    val strAwayLineupGoalkeeper: String? = null,
//    val strAwayLineupDefense: String? = null,
//    val strAwayLineupMidfield: String? = null,
//    val strAwayLineupForward: String? = null,
//    val strAwayLineupSubstitutes: String? = null
){
    companion object {
        const val TABLE_FAVORITES: String = "TABLE_FAVORITES"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val DATE_EVENT: String = "DATE_EVENT"

        const val STR_HOME_TEAM: String = "STR_HOME_TEAM"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val INT_HOME_SCORE: String = "INT_HOME_SCORE"
//        const val INT_HOME_SHOTS: String = "INT_HOME_SHOTS"
//        const val STR_HOME_GOAL_DETAILS: String = "STR_HOME_GOAL_DETAILS"
//        const val STR_HOME_LINEUP_GOALKEEPER: String = "STR_HOME_LINEUP_GOALKEEPER"
//        const val STR_HOME_LINEUP_DEFENSE: String = "STR_HOME_LINEUP_DEFENSE"
//        const val STR_HOME_LINEUP_MIDFIELD: String = "STR_HOME_LINEUP_MIDFIELD"
//        const val STR_HOME_LINEUP_FORWARD: String = "STR_HOME_LINEUP_FORWARD"
//        const val STR_HOME_LINEUP_SUBSTITUTES: String = "STR_HOME_LINEUP_SUBSTITUTES"

        const val STR_AWAY_TEAM: String = "STR_AWAY_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
        const val INT_AWAY_SCORE: String = "INT_AWAY_SCORE"
//        const val INT_AWAY_SHOTS: String = "INT_AWAY_SHOTS"
//        const val STR_AWAY_GOAL_DETAILS: String = "STR_AWAY_GOAL_DETAILS"
//        const val STR_AWAY_LINEUP_GOALKEEPER: String = "STR_AWAY_LINEUP_GOALKEEPER"
//        const val STR_AWAY_LINEUP_DEFENSE: String = "STR_AWAY_LINEUP_DEFENSE"
//        const val STR_AWAY_LINEUP_MIDFIELD: String = "STR_AWAY_LINEUP_MIDFIELD"
//        const val STR_AWAY_LINEUP_FORWARD: String = "STR_AWAY_LINEUP_FORWARD"
//        const val STR_AWAY_LINEUP_SUBSTITUTES: String = "STR_AWAY_LINEUP_SUBSTITUTES"
    }


}