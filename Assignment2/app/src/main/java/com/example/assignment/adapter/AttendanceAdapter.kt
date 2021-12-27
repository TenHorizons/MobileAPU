package com.example.assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.data.Datasource

class AttendanceAdapter : RecyclerView.Adapter<AttendanceAdapter.AttendanceHolder>() {
    private val TAG = "AttendanceAdapter"

    class AttendanceHolder(view: View):RecyclerView.ViewHolder(view){
        val nameTextView = view.findViewById<TextView>(R.id.item_attendanceLectureName)
        val numberTextView = view.findViewById<TextView>(R.id.item_attendanceNumber)
        val progressBar = view.findViewById<ProgressBar>(R.id.attendance_progressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.attendance_item,parent,false)
        return AttendanceHolder(layout)
    }

    override fun onBindViewHolder(holder: AttendanceHolder, position: Int) {
        val item = Datasource.attendances[position]
        holder.nameTextView.text = item.lecture.lectureName
        if(item.attendance){
            holder.numberTextView.text = "1/1"
            holder.progressBar.progress = 100
        }else {
            holder.numberTextView.text = "0/1"
            holder.progressBar.progress = 0
        }
    }

    override fun getItemCount(): Int = Datasource.attendances.size
}