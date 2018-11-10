package com.example.brownbox.matchschedule.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.model.LeagueItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat

class DetailActivity : AppCompatActivity(), LeagueDetailView,
    TeamDetailView {


    private lateinit var presenter: DetailPresenter
    private lateinit var teamPresenter: TeamPresenter
    private lateinit var leagueItem: LeagueItem

    private lateinit var eventId: String
    private var homeId: String = ""
    private var awayId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        leagueItem = intent.getParcelableExtra("Events")

        val request = ApiRepository()
        val gson = Gson()

        presenter = DetailPresenter(this, request, gson)
        teamPresenter = TeamPresenter(this, request, gson)

        eventId = leagueItem.idEvent!!
        homeId = leagueItem.idHomeTeam!!
        awayId = leagueItem.idAwayTeam!!

        presenter.getDetailEvent(eventId)
        teamPresenter.getDetailTeam(homeId, awayId)


    }
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showLeagueDetailList(data: List<LeagueDetailItem>?) {

        val date = SimpleDateFormat("EEE, d MMM yyyy")
            .format(SimpleDateFormat("yyyy-MM-dd")
                .parse(data!![0].dateEvent))

        detail_date.text = date

        if(data[0].intHomeScore.isNullOrEmpty() && data[0].intAwayScore.isNullOrEmpty()){
            detail_home_score.text = "0"
            detail_away_score.text = "0"
        }else{
            detail_home_score.text = data[0].intHomeScore
            detail_away_score.text = data[0].intAwayScore
        }

        detail_home.text = data[0].strHomeTeam
        detail_home_shots.text = data[0].intHomeShots

        detail_home_goal.text= data[0].strHomeGoalDetails?.replace(";","\n")
        detail_home_goalkeeper.text = data[0].strHomeLineupGoalkeeper
        detail_home_defense.text= data[0].strHomeLineupDefense?.replace(";","\n")
        detail_home_midfield.text= data[0].strHomeLineupMidfield?.replace(";","\n")
        detail_home_forward.text= data[0].strHomeLineupForward?.replace(";","\n")
        detail_home_subtitutes.text= data[0].strHomeLineupSubstitutes?.replace(";","\n")

        detail_away.text = data[0].strAwayTeam
        detail_away_shots.text = data[0].intAwayShots
        detail_away_goal.text= data[0].strAwayGoalDetails?.replace(";","\n")
        detail_away_goalkeeper.text = data[0].strAwayLineupGoalkeeper
        detail_away_defense.text= data[0].strAwayLineupDefense?.replace(";","\n")
        detail_away_midfield.text= data[0].strAwayLineupMidfield?.replace(";","\n")
        detail_away_forward.text= data[0].strAwayLineupForward?.replace(";","\n")
        detail_away_subtitutes.text= data[0].strAwayLineupSubstitutes?.replace(";","\n")

    }

    override fun showTeamDetailList(dataA: List<TeamDetailItem>, dataB: List<TeamDetailItem>) {
        Picasso.get().load(dataA.get(0).strTeamBadge).into(detail_home_logo)
        Picasso.get().load(dataB.get(0).strTeamBadge).into(detail_away_logo)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
