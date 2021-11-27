package com.example.android.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_title.view.*

class TestAdapter(
        private val context : Context, private val listener : ITestAdapter
) : RecyclerView.Adapter<TestAdapter.TestViewHolder>(), ISubtitleAdapter {

    private val titlesWithSubtitles = ArrayList<TitlesWithSubtitles>()

    inner class TestViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.tvTitle)
        val crossButton = itemView.findViewById<ImageView>(R.id.ivTitleCrossButton)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_title,parent,false)
        val viewHolder = TestViewHolder(view)
        viewHolder.crossButton.setOnClickListener {
            listener.onCrossButtonClickedTest(titlesWithSubtitles[viewHolder.bindingAdapterPosition].title)
        }
        viewHolder.textView.setOnClickListener{
            listener.onTitleClickedTest(titlesWithSubtitles[viewHolder.bindingAdapterPosition].title)
        }
        return viewHolder
    }
    override fun onBindViewHolder(holder: TestAdapter.TestViewHolder, position: Int) {
        holder.itemView.apply {
            tvTitle.text = titlesWithSubtitles[position].title.titleNote
        }
        val subtitles = titlesWithSubtitles[position].subtitles
        val subtitleAdapter = SubtitleAdapter(context,this)
        holder.itemView.rvSubtitles.adapter = subtitleAdapter
        holder.itemView.rvSubtitles.layoutManager = LinearLayoutManager(context)
        subtitleAdapter.updateList(subtitles)
    }

    override fun getItemCount(): Int {
        return titlesWithSubtitles.size
    }
    fun updateList(newList: List<TitlesWithSubtitles>){
        titlesWithSubtitles.clear()
        titlesWithSubtitles.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onSubtitleCrossButtonClicked(subtitle: Subtitle) {
        listener.onSubtitleCrossButtonClicked(subtitle)
    }

    override fun onSubtitleClicked(subtitle: Subtitle) {
        listener.onSubtitleClicked(subtitle)
    }

}

interface ITestAdapter{
    fun onCrossButtonClickedTest(title : Title)
    fun onTitleClickedTest(title : Title)
    fun onSubtitleCrossButtonClicked(subtitle: Subtitle)
    fun onSubtitleClicked(subtitle: Subtitle)

}