package com.example.assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.model.Attendance

class HomeAdapter(present:MutableList<Attendance>) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    private val TAG = "HomeAdapter"
    private val present = present

    class HomeHolder(val view: View):RecyclerView.ViewHolder(view){
        val itemText = view.findViewById<TextView>(R.id.item_homeText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val layout = LayoutInflater
                .from(parent.context)
            .inflate(R.layout.home_item,parent,false)
        return HomeHolder(layout)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val item = present[position]
        holder.itemText.text = item.lecture.lectureName
    }

    override fun getItemCount(): Int = present.size

}