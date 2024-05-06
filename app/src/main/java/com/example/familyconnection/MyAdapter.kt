package com.example.familyconnection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val myDataset: ArrayList<String>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activities_cardview, parent, false)
        return MyViewHolder(itemView)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvActivity: TextView = itemView.findViewById(R.id.tv_activity)

    fun bind(activity: String) {
        tvActivity.text = activity
    }
}

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val activity = myDataset[position]
    holder.bind(activity)
}

    override fun getItemCount() = myDataset.size
}