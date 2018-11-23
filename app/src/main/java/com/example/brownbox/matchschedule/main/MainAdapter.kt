package com.example.brownbox.matchschedule.main

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.model.LeagueItem
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import java.text.SimpleDateFormat
import java.util.*

class MainAdapter (private val context: Context, private val events: List<LeagueItem>, private val listener: (LeagueItem) -> Unit)
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
        p0.bindItem(events[p1], listener)
    }

    inner class LeagueItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        private val dateEvent: TextView = itemView.find(R.id.date_event)
        private val timeEvent: TextView = itemView.find(R.id.time_event)
        private val homeTeam: TextView = itemView.find(R.id.home_team)
        private val homeScore: TextView = itemView.find(R.id.home_score)
        private val awayTeam: TextView = itemView.find(R.id.away_team)
        private val awayScore: TextView = itemView.find(R.id.away_score)

        fun bindItem(events: LeagueItem, listener: (LeagueItem) -> Unit){

//            val date = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
//                .format(SimpleDateFormat("yyyy-MM-dd")
//                    .parse(events.dateEvent))

//            <!--TODO Date Time Belum sesuai -->
            val date = events.dateEvent.toString()
            val time = events.strTime.toString()

            val dateTime = toGMTFormat(date, time)

            dateEvent.text = dateTime
            timeEvent.text = "$date $time"
            homeTeam.text = events.strHomeTeam
            homeScore.text = events.intHomeScore
            awayTeam.text = events.strAwayTeam
            awayScore.text = events.intAwayScore

            itemView.setOnClickListener {
                listener(events)
            }

        }

    }

}

fun toGMTFormat(date: String, time: String): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"
    return formatter.parse(dateTime).toString()
}

class LeagueUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

            cardView {
                id = R.id.match_cardview
                lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(10)
                    rightMargin = dip(10)
                    leftMargin = dip(10)
                }


                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.VERTICAL

                    textView {
                        id = R.id.date_event
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        gravity = Gravity.CENTER

                    }

                    textView {
                        id = R.id.time_event
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        gravity = Gravity.CENTER

                    }

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER_HORIZONTAL

                        textView {
                            id = R.id.home_team
                            textSize = 18f
                            padding = dip(8)
                            singleLine = true
                            ellipsize = TextUtils.TruncateAt.END
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                            }
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
                                padding = dip(8)
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

}