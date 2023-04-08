package com.example.mvvmproject.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//deleted
object RetrofitClient {
    fun getInstance(): Retrofit {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor).build()

        val retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl("https://lucifer-quotes.vercel.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build()

        return retrofit
    }
}