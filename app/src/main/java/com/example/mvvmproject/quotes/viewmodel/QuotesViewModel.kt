package com.example.mvvmproject.quotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmproject.model.LuciferModel
import com.example.mvvmproject.repo.MainRepo
import com.example.mvvmproject.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(private val repo: MainRepo): ViewModel() {
    private val _quotesResponse : MutableSharedFlow<ApiResponse<List<LuciferModel>?>> = MutableSharedFlow()
    val quotesResponse : SharedFlow<ApiResponse<List<LuciferModel>?>> = _quotesResponse


    fun fetchQuotes(){
        viewModelScope.launch {
           val response =  repo.fetchQuotes()
            _quotesResponse.emit(response)
        }
    }
}