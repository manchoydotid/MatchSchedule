package com.example.brownbox.matchschedule.main

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.brownbox.matchschedule.detail.DetailActivity
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.model.LeagueItem
import org.jetbrains.anko.*
import java.text.SimpleDateFormat

class MainAdapter (private val events: List<LeagueItem>, private val context: Context)
    :RecyclerView.Adapter<MainAdapter.LeagueItemViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LeagueItemViewHolder {
        return LeagueItemViewHolder(
            LeagueUI().createView(
                AnkoContext.create(p0.context, p0)
            )
        )
    }

    override fun getItemCount(): Int = events.size


    override fun onBindViewHolder(p0: MainAdapter.LeagueItemViewHolder, p1: Int) {
        p0.bindItem(events[p1])
    }

    inner class LeagueItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        private val dateEvent: TextView = itemView.find(R.id.date_event)
        private val homeTeam: TextView = itemView.find(R.id.home_team)
        private val homeScore: TextView = itemView.find(R.id.home_score)
        private val awayTeam: TextView = itemView.find(R.id.away_team)
        private val awayScore: TextView = itemView.find(R.id.away_score)

        fun bindItem(events: LeagueItem){

            var date = SimpleDateFormat("EEE, d MMM yyyy")
                .format(SimpleDateFormat("yyyy-MM-dd")
                    .parse(events.dateEvent))

            dateEvent.text = date
            homeTeam.text = events.strHomeTeam
            homeScore.text = events.intHomeScore
            awayTeam.text = events.strAwayTeam
            awayScore.text = events.intAwayScore

            itemView.setOnClickListener {
                context.startActivity(context.intentFor<DetailActivity>("Events" to events))
            }

        }

    }

}

class LeagueUI: AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                textView {
                    id = R.id.date_event
                    textColor = resources.getColor(R.color.colorPrimary)
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    gravity = Gravity.CENTER

                }

                linearLayout {
                    lparams (width = matchParent, height = wrapContent)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_HORIZONTAL

                    textView {
                        id = R.id.home_team
                        textSize = 18f
                        padding = dip(8)
                        singleLine = true
                        ellipsize = TextUtils.TruncateAt.END
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                        weight = 1f
                    }

                    linearLayout {
                        gravity = Gravity.CENTER_VERTICAL

                        textView {
                            id = R.id.home_score
                            padding = dip(8)
                            textSize = 20f
                            setTypeface(null, Typeface.BOLD)
                        }.lparams {
                        width = wrapContent
                        height = wrapContent
                        }

                        textView {
                            text = "VS"
                        }.lparams {
                        width = wrapContent
                        height = wrapContent
                        }

                        textView {
                            id = R.id.away_score
                            padding= dip(8)
                            textSize = 20f
                            setTypeface(null, Typeface.BOLD)
                        }.lparams {
                            width = wrapContent
                            height = wrapContent
                        }
                    }

                    textView {
                        id = R.id.away_team
                        textSize = 18f
                        padding = dip(8)
                        singleLine = true
                        ellipsize = TextUtils.TruncateAt.END
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                        weight = 1f

                    }
                }
            }
        }
    }
}


