package com.seojongcheol.androidnews.list

import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.seojongcheol.androidnews.R
import com.seojongcheol.androidnews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val newsViewModel: MainViewModel by viewModels()
    private val newsAdapter = NewsAdapter()
    private var lastBackPressedTime = 0L
    private val finishTime = 1000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupRecyclerView()
        observeNews()
        setOnBackPressedListener()
    }

    private fun setOnBackPressedListener() {
        onBackPressedDispatcher.addCallback {
            val time = Date().time

            if (time - lastBackPressedTime < finishTime) {
                finishAffinity()
            } else {
                lastBackPressedTime = time
                Toast.makeText(this@MainActivity, "한 번 더 뒤로가기를 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
        }
    }

    private fun observeNews() {
        newsViewModel.news.observe(this) { articles ->
            newsAdapter.submitList(articles)
        }
    }
}