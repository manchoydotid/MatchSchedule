package com.example.brownbox.matchschedule.view.teamDetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.R.menu.fav_menu
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.model.DetailTeamModel.TeamDetailItem
import com.example.brownbox.matchschedule.favorite.TeamFavorites
import com.example.brownbox.matchschedule.adapter.TeamDetailPagerAdapter
import com.example.brownbox.matchschedule.db.teamDatabase
import com.example.brownbox.matchschedule.presenter.TeamDetailPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var teamDetailItem: TeamDetailItem
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teamId: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)


        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        val intent = intent
        teamId = intent.getStringExtra("idTeam")

        detail_viewpager.adapter = TeamDetailPagerAdapter(
            teamId,
            supportFragmentManager,
            2
        )
        favoriteState()
        val request = ApiRepository()
        val gson = Gson()

        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getDetailTeam(teamId)

    }

    private fun favoriteState(){
        teamDatabase.use {
            val result = select(TeamFavorites.TABLE_TEAM_FAVORITE)
                .whereArgs("(${TeamFavorites.TEAM_ID} = {idTeam})",
                    "idTeam" to teamId)
            val favorite = result.parseList(classParser<TeamFavorites>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamDetail(data: List<TeamDetailItem>) {
        teamDetailItem = TeamDetailItem(
            data[0].idTeam,
            data[0].strTeam,
            data[0].strTeamBadge,
            data[0].intFormedYear,
            data[0].strStadium,
            data[0].strDescriptionEN
        )

        detail_team_name.text = data[0].strTeam
        detail_formed_year.text = data[0].intFormedYear
        detail_stadium.text = data[0].strStadium
        Picasso.get().load(data[0].strTeamBadge).into(detail_team_badge)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(fav_menu, menu)
        menuItem = menu
        setFavorite()
        return true
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

    private fun addToFavorite(){
        if(this::teamDetailItem.isInitialized){
            try {
                teamDatabase.use {
                    insert(TeamFavorites.TABLE_TEAM_FAVORITE,
                        TeamFavorites.TEAM_ID to teamDetailItem.idTeam,
                        TeamFavorites.TEAM_NAME to teamDetailItem.strTeam,
                        TeamFavorites.TEAM_BADGE to teamDetailItem.strTeamBadge
                    )
                    Log.e("TeamBadge Detail: ", "${teamDetailItem.strTeamBadge}")
                }
                detail_team_root.snackbar("Added to favorites").show()
            }catch (e: SQLiteConstraintException){
                detail_team_root.snackbar(e.localizedMessage).show()
            }
        }
    }

    private fun removeFromFavorite(){
        try{
            teamDatabase.use {
                delete(TeamFavorites.TABLE_TEAM_FAVORITE, "(${TeamFavorites.TEAM_ID} = {idTeam})",
                    "idTeam" to teamId)
            }
            detail_team_root.snackbar("Removed to favorites").show()
        }catch (e: SQLiteConstraintException){
            detail_team_root.snackbar(e.localizedMessage).show()
        }
    }


    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                R.drawable.ic_added_to_favorites
            )
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                R.drawable.ic_add_to_favorites
            )
    }
}
