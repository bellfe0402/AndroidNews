package com.seojongcheol.androidnews.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seojongcheol.androidnews.databinding.ItemNewsBinding
import com.seojongcheol.androidnews.utils.TimeUtils

class NewsAdapter : ListAdapter<NewsItem, NewsAdapter.NewsViewHolder>(ArticleDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: NewsItem) {
            binding.newsTitle.text = article.title
            binding.newsDescription.text = article.description
            binding.publishedAt.text = TimeUtils.convertTime(article.publishedAt)
            if (article.urlToImage.isNullOrBlank() || article.urlToImage.contains(".gif")) {
                //TODO: urlToImage 없는 경우에 대한 예외 처리 필요
                Glide.with(binding.newsImage).load("").into(binding.newsImage)
            } else {
                Glide.with(binding.newsImage).load(article.urlToImage).into(binding.newsImage)
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