package com.example.mvvmproject.repo

import com.example.mvvmproject.model.LuciferModel
import com.example.mvvmproject.remote.ApiInterface
import com.example.mvvmproject.util.ApiResponse
import javax.inject.Inject

class MainRepo @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun fetchQuotes(): ApiResponse<List<LuciferModel>?> {
        val response = apiInterface.fetchApi()
        return if (response.isSuccessful) {
            ApiResponse.Success(response.body())
        } else {
            ApiResponse.Error(response.message())
        }
    }
}