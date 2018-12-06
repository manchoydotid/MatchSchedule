package com.example.brownbox.matchschedule.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.brownbox.matchschedule.view.teamDetail.TeamDescFragment
import com.example.brownbox.matchschedule.view.player.TeamPlayerFragment

class TeamDetailPagerAdapter (private val idTeam: String, fm: FragmentManager, private var tabCount: Int)
    : FragmentPagerAdapter(fm){

    override fun getItem(p0: Int): Fragment {
        return when(p0){
            0 -> {
                newInstance(idTeam)
            }
            1 -> {
                newInstancePlayer(idTeam)
            }else -> {
                return TeamPlayerFragment()
            }
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Description"
            else -> "Player"
        }
    }

    companion object {
        const val KEY_TEAM = "KEY_TEAM"
        const val KEY_TEAM_2 = "KEY_TEAM_2"

        fun newInstance(id: String): TeamDescFragment {
            val bindData = Bundle()
            bindData.putString(KEY_TEAM, id)

            val teamDescFragment = TeamDescFragment()
            teamDescFragment.arguments = bindData
            return teamDescFragment
        }

        fun newInstancePlayer(id: String): TeamPlayerFragment {
            val bindData = Bundle()
            bindData.putString(KEY_TEAM_2, id)

            val teamPlayerFragment = TeamPlayerFragment()
            teamPlayerFragment.arguments = bindData
            return teamPlayerFragment
        }

    }

}



















