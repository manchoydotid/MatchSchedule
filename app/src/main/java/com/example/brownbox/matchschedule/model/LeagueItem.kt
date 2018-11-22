package com.example.brownbox.matchschedule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueItem(
    var idEvent: String? = null,
    var dateEvent: String? = null,
    var strTime: String? = null,

    var strHomeTeam: String? = null,
    var idHomeTeam: String? = null,
    var intHomeScore: String? = null,

    var strAwayTeam: String? = null,
    var idAwayTeam: String? = null,
    var intAwayScore: String? = null

):Parcelable