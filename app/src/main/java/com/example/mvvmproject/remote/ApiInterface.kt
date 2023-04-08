package com.example.mvvmproject.remote

import com.example.mvvmproject.model.LuciferModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/quotes/50")
    fun luciferApi(): Call<List<LuciferModel>>

    @GET("api/quotes/50")
    suspend fun fetchApi(): Response<List<LuciferModel>>
}