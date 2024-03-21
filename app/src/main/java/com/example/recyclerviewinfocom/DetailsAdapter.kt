package com.example.recyclerviewinfocom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewinfocom.data.FetchDetailsModel

class DetailsAdapter(private val details: ArrayList<FetchDetailsModel>) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolderClass>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_list_item, parent, false)
        return ViewHolderClass(view)

    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        var currentItem = details[position]
        holder.name.text = currentItem.name
        holder.city.text = currentItem.city
        holder.age.text = currentItem.age.toString()
    }

    override fun getItemCount(): Int {
        return details.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val city = itemView.findViewById<TextView>(R.id.tv_city)
        val age = itemView.findViewById<TextView>(R.id.tv_age)
    }

}