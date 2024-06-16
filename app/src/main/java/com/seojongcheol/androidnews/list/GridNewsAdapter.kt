package com.seojongcheol.androidnews.list

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seojongcheol.androidnews.R
import com.seojongcheol.androidnews.databinding.ItemNewsGridBinding
import com.seojongcheol.androidnews.utils.TimeUtils
import com.seojongcheol.androidnews.webview.WebViewActivity

class GridNewsAdapter : ListAdapter<NewsItem, GridNewsAdapter.NewsViewHolder>(ArticleDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.bind(newsItem)
    }

    inner class NewsViewHolder(private val binding: ItemNewsGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: NewsItem) {
            binding.newsTitle.text = newsItem.title
            binding.newsDescription.text = newsItem.description
            binding.publishedAt.text = TimeUtils.convertTime(newsItem.publishedAt)
            Glide.with(binding.newsImage).load(newsItem.urlToImage).placeholder(R.mipmap.ic_launcher).into(binding.newsImage)
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    item.isVisited = true
                }
                val context = binding.root.context
                val intent = Intent(context, WebViewActivity::class.java).apply {
                    putExtra("URL", newsItem.url)
                }
                context.startActivity(intent)
                notifyItemChanged(position)
            }

            if (newsItem.isVisited) {
                binding.newsTitle.setTextColor(Color.RED)
            } else {
                binding.newsTitle.setTextColor(Color.BLACK)
            }
        }
    }

    class ArticleDiffCallback : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }
    }
}