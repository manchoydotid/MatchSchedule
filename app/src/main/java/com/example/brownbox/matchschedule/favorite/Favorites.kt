package com.example.brownbox.matchschedule.favorite


data class Favorites(
    val id: Long?,
    val idEvent: String?,
    val dateEvent: String?,

    val strHomeTeam: String?,
    val idHomeTeam: String?,
    val intHomeScore: String?,

    val strAwayTeam: String?,
    val idAwayTeam: String?,
    val intAwayScore: String?
){
    companion object {
        const val TABLE_FAVORITES: String = "TABLE_FAVORITES"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val DATE_EVENT: String = "DATE_EVENT"

        const val STR_HOME_TEAM: String = "STR_HOME_TEAM"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val INT_HOME_SCORE: String = "INT_HOME_SCORE"

        const val STR_AWAY_TEAM: String = "STR_AWAY_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
        const val INT_AWAY_SCORE: String = "INT_AWAY_SCORE"
    }
}