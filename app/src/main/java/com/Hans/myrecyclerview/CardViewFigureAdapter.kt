package com.gustam.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_cardview_figureww2.view.*

class CardViewFigureAdapter (private val listFigure: ArrayList<Figure>) :
    RecyclerView.Adapter<CardViewFigureAdapter.CardViewViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): CardViewFigureAdapter.CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_cardview_figureww2, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listFigure[position])
    }

    override fun getItemCount(): Int = listFigure.size
    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(figure: Figure) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(figure.photo)
                    .apply(RequestOptions().override(350, 550))
                    .into(img_item_photo)

                tv_item_name.text = figure.name
                tv_item_description.text = figure.description
                btn_set_favorite.setOnClickListener {
                    Toast.makeText(itemView.context, "Favorite ${figure.name}", Toast.LENGTH_SHORT)
                        .show()
                }

                btn_set_share.setOnClickListener {
                    Toast.makeText(itemView.context, "Share ${figure.name}", Toast.LENGTH_SHORT)
                        .show()
                }
                itemView.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "Kamu memilih ${figure.name}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}