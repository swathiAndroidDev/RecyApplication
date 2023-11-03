package com.example.recyapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AlbumsViewModel

    private lateinit var albumsList: RecyclerView
    private lateinit var loading: ProgressBar
    private lateinit var errorMsg: TextView
    private lateinit var adapter: AlbumsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       setupRecyclerView()

        loading = findViewById(R.id.progress_bar)
        errorMsg = findViewById(R.id.error_msg)

        viewModel = ViewModelProvider(this).get(AlbumsViewModel::class.java)

        viewModel.fetchAlbums()
        handleAlbumsResult()
    }

    private fun setupRecyclerView() {
        albumsList = findViewById(R.id.albums_list)
        val linearLayoutManager = LinearLayoutManager(this)
        albumsList.layoutManager = linearLayoutManager
        adapter = AlbumsAdapter()
        albumsList.adapter = adapter
    }

    private fun handleAlbumsResult() {
        lifecycleScope.launch {
            viewModel.albumsList.collect {
                when (it) {
                    is AlbumsViewModel.AlbumsResult.Loading -> {
                        loading.visibility = View.VISIBLE
                    }

                    is AlbumsViewModel.AlbumsResult.Success -> {
                        loading.visibility = View.GONE
                        errorMsg.visibility = View.GONE
                        adapter.swapList(it.list)
                    }

                    is AlbumsViewModel.AlbumsResult.Error -> {
                        loading.visibility = View.GONE
                        errorMsg.visibility = View.VISIBLE
                        errorMsg.text = it.t.message
                    }

                }

            }
        }
    }
}