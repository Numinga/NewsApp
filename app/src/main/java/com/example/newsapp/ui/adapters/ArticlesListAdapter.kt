package com.example.newsapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.kwabenaberko.newsapilib.models.Article

class ArticlesListAdapter(
    private val context: Context, private val items: List<Article>,
    private val listener: ItemClickListener
) :
    RecyclerView.Adapter<ArticlesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_article, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
        holder.bind(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView
        private val title: TextView
        private val desc: TextView
        private val source: TextView

        init {
            with(itemView) {
                image = findViewById(R.id.image)
                title = findViewById(R.id.title)
                desc = findViewById(R.id.description)
                source = findViewById(R.id.source)
            }
        }

        fun bind(item: Article) {
            Glide.with(context)
                .load(item.urlToImage)
                .placeholder(R.drawable.placeholder)
                .into(image);
            title.text = item.title
            desc.text = item.description
            source.text = item.source?.name
        }
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }
}