package com.gustam.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_grid_figure.view.img_item_photo

class GridFigureAdapter(private val listFigure: ArrayList<Figure>) : RecyclerView.Adapter<GridFigureAdapter.GridViewHolder>() {
    private var onItemClickCallBack: OnItemClickCallBack? = null

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GridFigureAdapter.GridViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_grid_figure, viewGroup, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridFigureAdapter.GridViewHolder, position: Int) {
        holder.bind(listFigure[position])
    }

    override fun getItemCount(): Int = listFigure.size

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(figure: Figure) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(figure.photo)
                    .apply(RequestOptions().override(350, 550))
                    .into(img_item_photo)

                itemView.setOnClickListener {onItemClickCallBack?.onItemClicked(figure)}
            }
        }
    }
    interface OnItemClickCallBack {
        fun onItemClicked(data: Figure)
    }
}