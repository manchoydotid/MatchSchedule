package com.example.brownbox.matchschedule.teams.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.detail.TeamDetailItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var teamDetailItem: TeamDetailItem
    private lateinit var presenter: TeamDetailPresenter
    private var teamId: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        val intent = intent
        teamId = intent.getStringExtra("idTeam")

        detail_viewpager.adapter = TeamDetailPagerAdapter(teamId, supportFragmentManager, 2)

        val request = ApiRepository()
        val gson = Gson()

        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getDetailTeam(teamId)

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamDetail(data: List<TeamDetailItem>) {
        teamDetailItem = TeamDetailItem(
            data[0].idTeam,
            data[0].strTeam,
            data[0].intFormedYear,
            data[0].strStadium,
            data[0].strTeamBadge,
            data[0].strDescriptionEN
        )

        detail_team_name.text = data[0].strTeam
        detail_formed_year.text = data[0].intFormedYear
        detail_stadium.text = data[0].strStadium
        Picasso.get().load(data[0].strTeamBadge).into(detail_team_badge)

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
