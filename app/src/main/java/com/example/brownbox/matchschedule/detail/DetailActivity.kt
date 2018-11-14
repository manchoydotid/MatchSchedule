package com.example.brownbox.matchschedule.detail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.example.brownbox.matchschedule.Favorites
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.R.drawable.ic_add_to_favorites
import com.example.brownbox.matchschedule.R.drawable.ic_added_to_favorites
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.database
import com.example.brownbox.matchschedule.model.LeagueItem
import com.example.brownbox.matchschedule.util.invisible
import com.example.brownbox.matchschedule.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity(), LeagueDetailView,
    TeamDetailView {

    private lateinit var presenter: DetailPresenter
    private lateinit var teamPresenter: TeamPresenter
    private lateinit var leagueDetailItem: LeagueDetailItem

    private lateinit var eventId: String
    private var homeId: String = ""
    private var awayId: String = ""

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        eventId = intent.getStringExtra("idEvent")
        homeId = intent.getStringExtra("idHome")
        awayId = intent.getStringExtra("idAway")

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()

        presenter = DetailPresenter(this, request, gson)
        teamPresenter = TeamPresenter(this, request, gson)
//
//        eventId = leagueDetailItem.idEvent!!
//        homeId = leagueDetailItem.idHomeTeam!!
//        awayId = leagueDetailItem.idAwayTeam!!

        presenter.getDetailEvent(eventId)
        teamPresenter.getDetailTeam(homeId, awayId)

        detail_swipeRefresh.onRefresh {
            presenter.getDetailEvent(eventId)
            teamPresenter.getDetailTeam(homeId, awayId)
        }
    }
    private fun favoriteState(){
        database.use{
            val result = select(Favorites.TABLE_FAVORITES)
                .whereArgs("(ID_EVENT = {id})",
                    "id" to eventId)
            val favorites = result.parseList(classParser<Favorites>())
            if (!favorites.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        detail_progressBar.visible()
    }

    override fun hideLoading() {
        detail_progressBar.invisible()
    }

    override fun showLeagueDetailList(data: List<LeagueDetailItem>) {
        leagueDetailItem = LeagueDetailItem(
            data[0].idEvent,
            data[0].dateEvent,
            data[0].strHomeTeam,
            data[0].idHomeTeam,
            data[0].intHomeScore,
            data[0].strHomeFormation,
            data[0].intHomeShots,
            data[0].strHomeGoalDetails,
            data[0].strHomeLineupGoalkeeper,
            data[0].strHomeLineupDefense,
            data[0].strHomeLineupMidfield,
            data[0].strHomeLineupForward,
            data[0].strHomeLineupSubstitutes,

            data[0].strAwayTeam,
            data[0].idAwayTeam,
            data[0].intAwayScore,
            data[0].strAwayFormation,
            data[0].intAwayShots,
            data[0].strAwayGoalDetails,
            data[0].strAwayLineupGoalkeeper,
            data[0].strAwayLineupDefense,
            data[0].strAwayLineupMidfield,
            data[0].strAwayLineupForward,
            data[0].strAwayLineupSubstitutes

        )

        val date = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
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
        Picasso.get().load(dataA[0].strTeamBadge).into(detail_home_logo)
        Picasso.get().load(dataB[0].strTeamBadge).into(detail_away_logo)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun addToFavorite(){
        try{
            database.use{
                insert(Favorites.TABLE_FAVORITES,
                    Favorites.ID_EVENT to leagueDetailItem.idEvent,
                    Favorites.DATE_EVENT to leagueDetailItem.dateEvent,
                    Favorites.STR_HOME_TEAM to leagueDetailItem.strHomeTeam,
                    Favorites.ID_HOME_TEAM to leagueDetailItem.idHomeTeam,
                    Favorites.INT_HOME_SCORE to leagueDetailItem.intHomeScore,

                    Favorites.STR_AWAY_TEAM to leagueDetailItem.strAwayTeam,
                    Favorites.ID_AWAY_TEAM to leagueDetailItem.idAwayTeam,
                    Favorites.INT_AWAY_SCORE to leagueDetailItem.intAwayScore
                )
            }
            detail_swipeRefresh.snackbar("Added to favorites").show()
        }catch (e: SQLiteConstraintException){
            detail_swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try{
            database.use {
                delete(Favorites.TABLE_FAVORITES, "(ID_EVENT = {id})",
                    "id" to eventId)
            }
            detail_swipeRefresh.snackbar("Removed to favorites").show()
        }catch (e: SQLiteConstraintException){
            detail_swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }


}