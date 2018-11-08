package com.example.brownbox.matchschedule


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.main.MainAdapter
import com.example.brownbox.matchschedule.main.MainPresenter
import com.example.brownbox.matchschedule.main.MainView
import com.example.brownbox.matchschedule.model.LeagueItem
import com.example.brownbox.matchschedule.util.invisible
import com.example.brownbox.matchschedule.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LastFragment : Fragment(), MainView {

    private var events: MutableList<LeagueItem> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    val legaueId = "4328"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = MainAdapter(events)
        rv_fragment.layoutManager = LinearLayoutManager(requireContext())
        rv_fragment.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getPastLeagueList(legaueId)

        swipeRefresh.onRefresh {
            presenter.getPastLeagueList("4328")
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
