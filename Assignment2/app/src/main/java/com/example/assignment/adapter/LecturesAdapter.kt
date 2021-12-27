package com.example.assignment.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.LectureActivity
import com.example.assignment.R
import com.example.assignment.data.Datasource

class LecturesAdapter : RecyclerView.Adapter<LecturesAdapter.LecturesHolder>() {
    private val TAG = "LecturesAdapter"

    class LecturesHolder(val view: View): RecyclerView.ViewHolder(view){
        val textView = view.findViewById<TextView>(R.id.item_lectureName)
        val button = view.findViewById<Button>(R.id.lectures_button)
    }

    override fun getItemCount():Int = Datasource.lectures.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LecturesHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.lectures_item,parent,false)
        return LecturesHolder(layout)
    }

    override fun onBindViewHolder(holder: LecturesHolder, position: Int) {
        val item = Datasource.lectures[position]
        val context = holder.view.context
        holder.textView.text = item.lectureName
        holder.button.setOnClickListener {
            val intent = Intent(context, LectureActivity::class.java)
            intent.putExtra("lecturePosition",position.toString())
//            val checkExtra = intent.extras?.getString("lecturePosition").toString()
//            Log.d(TAG,"check extra: $checkExtra")
            context.startActivity(intent)
        }
    }
}