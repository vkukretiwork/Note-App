package com.example.android.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_title.view.*

class TitleAdapter(
       private val context : Context, private val listener : ITitleAdapter
) : RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

    private val titles = ArrayList<Title>()

    inner class TitleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.tvTitle)
        val crossButton = itemView.findViewById<ImageView>(R.id.ivTitleCrossButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_title,parent,false)
        val viewHolder = TitleViewHolder(view)
        viewHolder.crossButton.setOnClickListener {
            listener.onCrossButtonClicked(titles[viewHolder.bindingAdapterPosition])
        }
        viewHolder.textView.setOnClickListener{
            listener.onTitleClicked(titles[viewHolder.bindingAdapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        holder.itemView.apply {
            tvTitle.text = titles[position].titleNote
        }
    }

    override fun getItemCount(): Int {
       return titles.size
    }

    fun updateList(newList: List<Title>){
        titles.clear()
        titles.addAll(newList)
        notifyDataSetChanged()
    }
}

interface ITitleAdapter{
    fun onCrossButtonClicked(title : Title)
    fun onTitleClicked(title : Title)
}