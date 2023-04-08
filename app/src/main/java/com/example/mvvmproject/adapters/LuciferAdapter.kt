package com.example.mvvmproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmproject.R
import com.example.mvvmproject.model.LuciferModel


class LuciferAdapter(private val dataList: List<LuciferModel>) :
    RecyclerView.Adapter<LuciferAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.quote.text = data.quote
        holder.author.text = data.author
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val quote: TextView = itemView.findViewById(R.id.txt_quote)
        val author: TextView = itemView.findViewById(R.id.txt_author)

    }
}