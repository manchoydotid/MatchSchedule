package com.example.brownbox.matchschedule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueItem(
    val idEvent: String,
    val dateEvent: String,

    val strHomeTeam: String,
    val idHomeTeam: String,
    val intHomeScore: String,

    val strAwayTeam: String,
    val idAwayTeam: String,
    val intAwayScore: String

):Parcelable