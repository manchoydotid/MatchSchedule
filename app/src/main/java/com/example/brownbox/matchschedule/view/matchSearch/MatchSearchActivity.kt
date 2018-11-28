package com.example.brownbox.matchschedule.view.matchSearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.widget.ProgressBar
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.adapter.MainAdapter
import com.example.brownbox.matchschedule.model.EventLeagueModel.LeagueItem
import com.example.brownbox.matchschedule.util.invisible
import com.example.brownbox.matchschedule.util.visible
import com.example.brownbox.matchschedule.view.matchDetail.DetailMatchActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_match_search.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MatchSearchActivity : AppCompatActivity(), MatchSearchView {

    private lateinit var listEvent: RecyclerView
    private var event: MutableList<LeagueItem> = mutableListOf()
    private lateinit var presenter: MatchSearchPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_search)

        progressBar = findViewById(R.id.search_match_pBar)

        val keyword = intent.getStringExtra("keyword")

        adapter = MainAdapter(this, event) {
            startActivity<DetailMatchActivity>(
                "idEvent" to "${it.idEvent}",
                "idHome" to "${it.idHomeTeam}",
                "idAway" to "${it.idAwayTeam}"
            )
        }

        listEvent = find(R.id.search_match_rv)
        listEvent.layoutManager = LinearLayoutManager(this)
        listEvent.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchSearchPresenter(this, request, gson)
        presenter.getEventSearch(keyword)

    }

    override fun showLoading() {
        progressBar.visible()
        search_match_empty.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        search_match_empty.invisible()
    }

    override fun emptyState() {
        progressBar.invisible()
        search_match_empty.visible()
    }

    override fun showEventList(data: List<LeagueItem>) {
        event.clear()
        event.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.search_item)?.actionView as android.support.v7.widget.SearchView?
        searchView?.queryHint = "Search Match"

        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.getEventSearch(newText)
                return false
            }
        })
        return true
    }


}
