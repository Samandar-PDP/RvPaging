package com.sdk.rvpagination103

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdk.rvpagination103.adapter.RickAndMortyAdapter
import com.sdk.rvpagination103.databinding.ActivityMainBinding
import com.sdk.rvpagination103.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val rickAndMortyAdapter by lazy { RickAndMortyAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        lifecycleScope.launch {
            viewModel.getCharacterList().collectLatest {
                binding.progressBar.isVisible = false
                rickAndMortyAdapter.submitData(it)
            }
        }

        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = rickAndMortyAdapter
            }
        }
    }
}