package com.example.brownbox.matchschedule.favorite

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.R.id.team_fav_badge
import com.example.brownbox.matchschedule.R.id.team_fav_name
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class TeamFavoritesAdapter(private val favorite: List<TeamFavorites>,
                           private val listener: (TeamFavorites) -> Unit) :RecyclerView.Adapter<TeamFavoriteViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamFavoriteViewHolder {
        return TeamFavoriteViewHolder(TeamFavoriteUI().createView(AnkoContext.create(p0.context, p0)))

    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(p0: TeamFavoriteViewHolder, p1: Int) {
        p0.bindItem(favorite[p1], listener)
    }

}

class TeamFavoriteUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

            cardView {
                id = R.id.team_fav_cardview
                lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(10)
                    rightMargin = dip(10)
                    leftMargin = dip(10)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.team_fav_badge
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.team_fav_name
                        textSize = 16f
                    }.lparams {
                        margin = dip(15)
                    }

                }
            }

        }
    }

}


class TeamFavoriteViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val teamBadge: ImageView = view.find(team_fav_badge)
    private val teamName: TextView = view.find(team_fav_name)

    fun bindItem(favorite: TeamFavorites, listener: (TeamFavorites) -> Unit){
        Picasso.get().load(favorite.strTeamBadge).into(teamBadge)
        teamName.text = favorite.strTeam
        itemView.setOnClickListener { listener(favorite) }
    }
}