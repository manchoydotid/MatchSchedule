package com.example.brownbox.matchschedule.view.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.model.TeamModel.Team
import com.example.brownbox.matchschedule.adapter.TeamsAdapter
import com.example.brownbox.matchschedule.presenter.TeamsPresenter
import com.example.brownbox.matchschedule.util.invisible
import com.example.brownbox.matchschedule.util.visible
import com.example.brownbox.matchschedule.view.teamDetail.TeamDetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh


class TeamsFragment : Fragment(), TeamsView {


    private lateinit var listTeam: RecyclerView
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var leagueName: String
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyState: TextView


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        teams_spinner.adapter = spinnerAdapter

        adapter = TeamsAdapter(teams) {
            context?.startActivity<TeamDetailActivity>(
                "idTeam" to "${it.idTeam}"
            )
        }
        emptyState = view?.find(R.id.team_empty_tv) as TextView
        progressBar = view?.findViewById(R.id.teams_pBar) as ProgressBar
        swipeRefresh = view?.findViewById(R.id.teams_swipeRefresh) as SwipeRefreshLayout
        listTeam = view?.findViewById(R.id.teams_rv) as RecyclerView
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
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun showLoading() {
        progressBar.visible()
        emptyState.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        emptyState.invisible()
    }

    override fun emptyState() {
        progressBar.invisible()
        listTeam.invisible()
        emptyState.visible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.search_item)?.actionView as android.support.v7.widget.SearchView?
        searchView?.queryHint = "Search team"

        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.getTeamSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.getTeamSearch(newText)
                return false
            }
        })
    }

}
