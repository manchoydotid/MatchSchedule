package com.example.brownbox.matchschedule.api

import com.example.brownbox.matchschedule.BuildConfig

object TheSportDBApi {



    fun getNextLeague(leagueId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventsnextleague.php?id="+ leagueId
    }

    fun getPastLeague(leagueId: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventspastleague.php?id="+ leagueId
    }

    fun getLeagueDetail(eventId: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookupevent.php?id="+ eventId
    }

    fun getTeamDetail(teamId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "lookupteam.php?id"+ teamId
    }
}