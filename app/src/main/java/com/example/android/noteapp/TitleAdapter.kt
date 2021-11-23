package com.example.android.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_title.view.*

class TitleAdapter(
    var titles : List<Title>
) : RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

    inner class TitleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_title,parent,false)
        return TitleViewHolder(view)
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        holder.itemView.apply {
            tvTitle.text = titles[position].title
        }
    }

    override fun getItemCount(): Int {
       return titles.size
    }
}