package com.example.brownbox.matchschedule.teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.teams.detail.TeamDetailActivity
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.util.invisible
import com.example.brownbox.matchschedule.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


class TeamsFragment : Fragment(), TeamsView {

    private lateinit var listTeam: RecyclerView
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var leagueName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        teams_spinner.adapter = spinnerAdapter

        adapter = TeamsAdapter(teams){
            context?.startActivity<TeamDetailActivity>(
            "idTeam" to "${it.idTeam}")
        }
        listTeam = view!!.findViewById(R.id.teams_rv)
        listTeam.layoutManager = LinearLayoutManager(requireContext())
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)

        teams_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = teams_spinner.selectedItem.toString()
                presenter.getTeamList(leagueName.replace(" ","%20"))
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        teams_swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName.replace(" ", "%20"))
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun showLoading() {
        teams_pBar.visible()
    }

    override fun hideLoading() {
        teams_pBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        teams_swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }


}
