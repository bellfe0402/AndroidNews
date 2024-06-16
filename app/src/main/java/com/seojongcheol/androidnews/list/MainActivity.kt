package com.seojongcheol.androidnews.list

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.seojongcheol.androidnews.R
import com.seojongcheol.androidnews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val newsViewModel: MainViewModel by viewModels()
    private val listNewsAdapter = ListNewsAdapter()
    private val gridNewsAdapter = GridNewsAdapter()
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
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE || resources.configuration.screenWidthDp >= 600) {
                layoutManager = GridLayoutManager(this@MainActivity, 3)
                adapter = gridNewsAdapter
            } else {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = listNewsAdapter
            }
        }
    }

    private fun observeNews() {
        newsViewModel.news.observe(this) { articles ->
            listNewsAdapter.submitList(articles)
            gridNewsAdapter.submitList(articles)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        updateLayoutManager(newConfig.orientation, newConfig.screenWidthDp)
    }

    private fun updateLayoutManager(orientation: Int, screenWidthDp: Int) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE && screenWidthDp >= 600) {
            binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        } else {
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
}