package com.example.mentorme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Card_Adapter(private val dataList: ArrayList<DataClass_Card>) : RecyclerView.Adapter<Card_Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cards_layout,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.name.setText(currentItem.name)
        holder.role.setText(currentItem.role)
        holder.price.setText(currentItem.price)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name :TextView = itemView.findViewById(R.id.name)
        val role :TextView= itemView.findViewById(R.id.role)
        val price : TextView= itemView.findViewById(R.id.price)
    }
}