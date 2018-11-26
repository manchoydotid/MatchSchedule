package com.example.brownbox.matchschedule


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.detail.DetailMatchActivity
import com.example.brownbox.matchschedule.main.MainAdapter
import com.example.brownbox.matchschedule.main.MainPresenter
import com.example.brownbox.matchschedule.main.MainView
import com.example.brownbox.matchschedule.model.LeagueItem
import com.example.brownbox.matchschedule.util.invisible
import com.example.brownbox.matchschedule.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.startActivity


class NextFragment : Fragment(), MainView {

    private lateinit var listEvent: RecyclerView
    private var events: MutableList<LeagueItem> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var leagueName: String
    private lateinit var progressBar: ProgressBar


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        match_spinner.adapter = spinnerAdapter

        adapter = MainAdapter(requireContext(), events){
            startActivity<DetailMatchActivity>(
                "idEvent" to "${it.idEvent}",
                "idHome" to "${it.idHomeTeam}",
                "idAway" to "${it.idAwayTeam}")
        }

        progressBar = view!!.findViewById(R.id.pBar)
        listEvent = view!!.findViewById(R.id.rv_fragment)
        listEvent.layoutManager = LinearLayoutManager(requireContext())
        listEvent.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        match_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {


            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = match_spinner.selectedItem.toString()
                when(leagueName){
                    "English Premier League" -> presenter.getNextLeagueList("4328")
                    "German Bundesliga" -> presenter.getNextLeagueList("4331")
                    "Italian Serie A" -> presenter.getNextLeagueList("4332")
                    "French Ligue 1" -> presenter.getNextLeagueList("4334")
                    "Spanish La Liga" -> presenter.getNextLeagueList("4335")
                    "Netherlands Eredivisie" -> presenter.getNextLeagueList("4337")
                    else -> presenter.getPastLeagueList("4328")
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
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
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
