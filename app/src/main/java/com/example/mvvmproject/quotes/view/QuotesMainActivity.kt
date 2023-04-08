package com.example.mvvmproject.quotes.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmproject.adapters.LuciferAdapter
import com.example.mvvmproject.databinding.ActivityMainBinding
import com.example.mvvmproject.quotes.viewmodel.QuotesViewModel
import com.example.mvvmproject.util.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuotesMainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val viewModel: QuotesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate((layoutInflater))
        setContentView(binding?.root)
        observe()
        luciferApi()
    }

    private fun luciferApi() {
        viewModel.fetchQuotes()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.quotesResponse.collect {
                when (it) {
                    is ApiResponse.Loading -> {

                    }
                    is ApiResponse.Success -> {
                        binding?.recyclerView?.layoutManager = LinearLayoutManager(this@QuotesMainActivity)
                        val adapter = it.data?.let { it1 -> LuciferAdapter(it1) }
                        binding?.recyclerView?.adapter = adapter
                    }
                    is ApiResponse.Error -> {
                        Toast.makeText(
                            this@QuotesMainActivity,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}