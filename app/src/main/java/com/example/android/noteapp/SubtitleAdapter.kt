package com.example.android.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_add_subtitle.view.*

class SubtitleAdapter(
        private val context : Context, private val listener : ISubtitleAdapter
) : RecyclerView.Adapter<SubtitleAdapter.SubtitleViewHolder>() {

    private val subtitles = ArrayList<Subtitle>()

    inner class SubtitleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val subtitleTextView = itemView.findViewById<TextView>(R.id.tvSubtitle)
        val subtitleCrossButton = itemView.findViewById<ImageView>(R.id.ivSubtitleCrossButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtitleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_add_subtitle,parent,false)
        val viewHolder = SubtitleViewHolder(view)
        viewHolder.subtitleCrossButton.setOnClickListener {
            listener.onSubtitleCrossButtonClicked(subtitles[viewHolder.bindingAdapterPosition])
        }
        viewHolder.subtitleTextView.setOnClickListener{
            listener.onSubtitleClicked(subtitles[viewHolder.bindingAdapterPosition])
//            Toast.makeText(context,"you clicked on subtitle",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SubtitleAdapter.SubtitleViewHolder, position: Int) {
        holder.itemView.apply {
            tvSubtitle.text = subtitles[position].subtitleNote
        }
    }

    override fun getItemCount(): Int {
        return subtitles.size
    }

    fun updateList(newList: List<Subtitle>){
        subtitles.clear()
        subtitles.addAll(newList)
        notifyDataSetChanged()
    }
}

interface ISubtitleAdapter{
    fun onSubtitleCrossButtonClicked(subtitle: Subtitle)
    fun onSubtitleClicked(subtitle: Subtitle)
}

