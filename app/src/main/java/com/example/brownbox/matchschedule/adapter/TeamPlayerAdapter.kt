package com.example.brownbox.matchschedule.adapter

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.R.id.*
import com.example.brownbox.matchschedule.model.TeamPlayerModel.TeamPlayerItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class TeamPlayerAdapter (private val player: List<TeamPlayerItem>,
                         private val listener: (TeamPlayerItem) -> Unit) : RecyclerView.Adapter<PlayerViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerViewHolder {
        return PlayerViewHolder(
            PlayerUI().createView(
                AnkoContext.create(p0.context, p0)
            )
        )
    }

    override fun getItemCount(): Int = player.size

    override fun onBindViewHolder(p0: PlayerViewHolder, p1: Int) {
        p0.bindItem(player[p1], listener)
    }

}
class PlayerUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

            cardView {
                id = R.id.player_cardview
                lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(5)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = player_photo
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = player_name
                        textSize = 16f
                        ellipsize = TextUtils.TruncateAt.END
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                        weight = 1f
                    }

                    textView {
                        id = player_position
                        textSize = 16f
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }
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

class PlayerViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val playerPhoto: ImageView = view.find(player_photo)
    private val playerName: TextView = view.find(player_name)
    private val playerPosition: TextView = view.find(player_position)

    fun bindItem(player: TeamPlayerItem, listener: (TeamPlayerItem) -> Unit){
        playerName.text = player.strPlayer
        playerPosition.text = player.strPosition
        Picasso.get().load(player.strCutout).into(playerPhoto)
        itemView.setOnClickListener { listener(player) }
    }
}