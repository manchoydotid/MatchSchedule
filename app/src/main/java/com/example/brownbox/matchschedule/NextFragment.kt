package com.example.brownbox.matchschedule


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.detail.DetailActivity
import com.example.brownbox.matchschedule.main.MainAdapter
import com.example.brownbox.matchschedule.main.MainPresenter
import com.example.brownbox.matchschedule.main.MainView
import com.example.brownbox.matchschedule.model.LeagueItem
import com.example.brownbox.matchschedule.util.invisible
import com.example.brownbox.matchschedule.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


class NextFragment : Fragment(), MainView {

    private var events: MutableList<LeagueItem> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private val legaueId = "4328"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        adapter = MainAdapter(events,requireContext())
        adapter = MainAdapter(ctx, events){
            startActivity<DetailActivity>(
                "idEvent" to "${it.idEvent}",
                "idHome" to "${it.idHomeTeam}",
                "idAway" to "${it.idAwayTeam}")
        }
        rv_fragment.layoutManager = LinearLayoutManager(requireContext())
        rv_fragment.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getNextLeagueList(legaueId)
        swipeRefresh.onRefresh {
            presenter.getNextLeagueList("4328")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<LeagueItem>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
