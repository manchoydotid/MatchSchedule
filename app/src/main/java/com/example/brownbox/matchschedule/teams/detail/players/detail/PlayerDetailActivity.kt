package com.example.brownbox.matchschedule.teams.detail.players.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.api.ApiRepository
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {

    private lateinit var playerDetailItem: PlayerDetailItem
    private lateinit var presenter: PlayerDetailPresenter
    private var playerId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        supportActionBar?.title = "Player Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        playerId = intent.getStringExtra("idPlayer")

        val request = ApiRepository()
        val gson = Gson()

        presenter = PlayerDetailPresenter(this, request, gson)
        presenter.getPlayerDetail(playerId)

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showPlayerDetail(data: List<PlayerDetailItem>) {
        playerDetailItem = PlayerDetailItem(
            data[0].idPlayer,
            data[0].strPlayer,
            data[0].strPosition,
            data[0].strHeight,
            data[0].strWeight,
            data[0].strDescriptionEN,
            data[0].strFanart1
        )
        Picasso.get().load(data[0].strFanart1).into(detail_player_fanart)
        player_name_detail.text = data[0].strPlayer
        player_position_detail.text = data[0].strPosition
        player_weight.text = data[0].strWeight
        player_height.text = data[0].strHeight
        player_desc.text = data[0].strDescriptionEN


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
