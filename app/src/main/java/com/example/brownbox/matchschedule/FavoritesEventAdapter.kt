package com.example.brownbox.matchschedule

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.brownbox.matchschedule.detail.DetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class FavoritesEventAdapter(private val context: Context, private val favorites: List<Favorites>, private val listener: (Favorites) -> Unit)
    : RecyclerView.Adapter<FavoritesEventAdapter.FavoritesViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoritesViewHolder {
        return FavoritesViewHolder(FavoritesUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(p0: FavoritesViewHolder, p1: Int) {
        p0.bindItem(favorites[p1], listener)
    }

    inner class FavoritesViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val dateEvent: TextView = itemView.find(R.id.date_event)
        private val homeTeam: TextView = itemView.find(R.id.home_team)
        private val homeScore: TextView = itemView.find(R.id.home_score)
        private val awayTeam: TextView = itemView.find(R.id.away_team)
        private val awayScore: TextView = itemView.find(R.id.away_score)

        fun bindItem(favorites: Favorites, listener: (Favorites) -> Unit){
            val date = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
                .format(
                    SimpleDateFormat("yyyy-MM-dd")
                        .parse(favorites.dateEvent))

            dateEvent.text = date
            homeTeam.text = favorites.strHomeTeam
            homeScore.text = favorites.intHomeScore
            awayTeam.text = favorites.strAwayTeam
            awayScore.text = favorites.intAwayScore

            itemView.setOnClickListener {
                listener(favorites)
            }
        }
    }
}


class FavoritesUI: AnkoComponent<ViewGroup> {
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
                            text = "vs"
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