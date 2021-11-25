package com.example.android.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_title.view.*

class TestAdapter(
        private val context : Context, private val listener : ITestAdapter
) : RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    private val titlesWithSubtitles = ArrayList<TitlesWithSubtitles>()

    inner class TestViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.tvTitle)
        val crossButton = itemView.findViewById<ImageView>(R.id.ivTitleCrossButton)
//        val subtitleList = itemView.findViewById<LinearLayout>(R.id.llSubtitles)
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
//        val llLinearLayout = holder.itemView.llSubtitles
//        val subtitles = titlesWithSubtitles[position].subtitles
//
//        for(subtitle in subtitles) {
//            val view = LayoutInflater.from(context).inflate(R.layout.row_add_subtitle, llLinearLayout, false)
//            val subtitleTextView = view.findViewById<TextView>(R.id.tvSubtitle)
//            val subtitleCrossButton = view.findViewById<ImageView>(R.id.ivSubtitleCrossButton)
//            subtitleCrossButton.setOnClickListener {
//                Toast.makeText(context, "you clicked on cross", Toast.LENGTH_SHORT).show()
//            }
//            subtitleTextView.text = subtitle.subtitleNote
//            llLinearLayout.addView(view)
//        }
    }

    override fun getItemCount(): Int {
        return titlesWithSubtitles.size
    }
    fun updateList(newList: List<TitlesWithSubtitles>){
        titlesWithSubtitles.clear()
        titlesWithSubtitles.addAll(newList)
        notifyDataSetChanged()
    }
}

interface ITestAdapter{
    fun onCrossButtonClickedTest(title : Title)
    fun onTitleClickedTest(title : Title)
}