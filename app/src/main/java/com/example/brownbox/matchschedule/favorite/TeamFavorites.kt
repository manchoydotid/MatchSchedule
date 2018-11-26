package com.example.brownbox.matchschedule.favorite

data class TeamFavorites(val id: Long?, val idTeam: String?,
                    val strTeam: String?, val strTeamBadge:String?){

    companion object {
        const val TABLE_TEAM_FAVORITE: String = "TABLE_TEAM_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
    }

}