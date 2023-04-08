package com.example.mvvmproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmproject.adapters.LuciferAdapter
import com.example.mvvmproject.databinding.ActivityMainBinding
import com.example.mvvmproject.model.LuciferModel
import com.example.mvvmproject.remote.ApiInterface
import com.example.mvvmproject.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate((layoutInflater))
        setContentView(binding?.root)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        luciferApi()
    }

    private fun luciferApi() {
        val apiInterface = RetrofitClient.getInstance().create(ApiInterface::class.java)
        apiInterface.luciferApi()
            .enqueue(object : Callback<List<LuciferModel>> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<List<LuciferModel>>,
                    response: Response<List<LuciferModel>>
                ) {
                    if (response.code() == 200 || response.code() == 201) {
                        val adapter = response.body()?.let { LuciferAdapter(it) }
                        binding?.recyclerView?.adapter = adapter
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<LuciferModel>>, t: Throwable) {
                    Log.e("TAG", "onFailure: $t")
                }
            })
    }
}