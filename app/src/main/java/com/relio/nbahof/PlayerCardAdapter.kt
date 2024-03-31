package com.relio.nbahof


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation


class PlayerCardAdapter(private val playerList: ArrayList<Player>, private val onClick: (Player) -> Unit): RecyclerView.Adapter<PlayerCardAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerImageView: ImageView = itemView.findViewById(R.id.player_card_image_view)
        val playerNameTextView: TextView = itemView.findViewById(R.id.player_card_name_tv)
        val playerPositionTextView: TextView = itemView.findViewById(R.id.player_card_position_tv)
        val playerTeamImageView: ImageView = itemView.findViewById(R.id.player_card_team_image_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.player_card, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        holder.playerTeamImageView.load(playerList[position].playerTeam)
        holder.playerImageView.load(playerList[position].imageUrl) {
                transformations(RoundedCornersTransformation(radius = 32f))
            }
        holder.playerNameTextView.text = playerList[position].name
        holder.playerPositionTextView.text = playerList[position].position
        holder.itemView.setOnClickListener {
            onClick(playerList[position])
        }
    }

}