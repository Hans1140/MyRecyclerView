package com.gustam.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_figureww2.view.*

class ListFigureAdapter(private val listFigure: ArrayList<Figure>) : RecyclerView.Adapter<ListFigureAdapter.ListViewHolder>() {
    private  var onItemClickCallback: OnItemClickCallBack? = null

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_figureww2, viewGroup, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
    holder.bind(listFigure[position])
    }
    override fun getItemCount(): Int =listFigure.size
        inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       fun bind(figure: Figure){
           with(itemView) {
               Glide.with(itemView.context)
                   .load(figure.photo)
                   .apply(RequestOptions().override(55, 55))
                   .into(img_item_photo)
               tv_item_name.text = figure.name
               tv_item_description.text = figure.description

               itemView.setOnClickListener{ onItemClickCallback?.onItemClicked(figure)}
           }
       }
    }
    interface OnItemClickCallBack {
        fun onItemClicked(data: Figure)
    }
}