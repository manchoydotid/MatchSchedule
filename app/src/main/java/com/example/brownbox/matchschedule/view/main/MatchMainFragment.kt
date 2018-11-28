package com.example.brownbox.matchschedule.view.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.example.brownbox.matchschedule.view.matchSearch.MatchSearchActivity
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.adapter.MatchPagerAdapter
import kotlinx.android.synthetic.main.fragment_match_main.*
import org.jetbrains.anko.startActivity





class MatchMainFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewpager_main.adapter = MatchPagerAdapter(childFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_main, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.search_item)?.actionView as android.support.v7.widget.SearchView?
        searchView?.queryHint = "Search team"

        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                context?.startActivity<MatchSearchActivity>("keyword" to query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }



}
