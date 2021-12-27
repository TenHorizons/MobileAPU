package com.example.assignment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.model.Questions

class QuizAdapter(questions: MutableList<Questions>) :
    RecyclerView.Adapter<QuizAdapter.QuizHolder>() {
    companion object {
        var checks = arrayOf(false, false, false)
    }

    fun getScore(): Int {
        var score = 0
        for (check in checks) {
            if (check) {
                score++
            }
        }
        return score
    }

    private val TAG = "QuizAdapter"
    val questions = questions

    class QuizHolder(view: View) : RecyclerView.ViewHolder(view) {
        val question = view.findViewById<TextView>(R.id.item_question)
        val group = view.findViewById<RadioGroup>(R.id.item_radio_group)
        val option_1 = view.findViewById<RadioButton>(R.id.item_option_1)
        val option_2 = view.findViewById<RadioButton>(R.id.item_option_2)
        val option_3 = view.findViewById<RadioButton>(R.id.item_option_3)
        val option_4 = view.findViewById<RadioButton>(R.id.item_option_4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.quiz_item, parent, false)
        return QuizHolder(layout)
    }

    override fun onBindViewHolder(holder: QuizHolder, position: Int) {
        val item = questions[position]
        Log.d(TAG, "item: ${item.option_1}, ${item.option_2}, ${item.option_3}, ${item.option_4}")
        holder.question.text = item.question
        holder.option_1.text = item.option_1
        holder.option_2.text = item.option_2
        holder.option_3.text = item.option_3
        holder.option_4.text = item.option_4
        holder.group.setOnCheckedChangeListener { group, checkedId ->
            checks[position] = item.answer == group.findViewById<RadioButton>(checkedId).text
        }
    }

    override fun getItemCount(): Int = questions.size
}