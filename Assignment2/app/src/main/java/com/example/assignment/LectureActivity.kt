package com.example.assignment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.adapter.QuizAdapter
import com.example.assignment.data.Datasource
import com.example.assignment.model.Lectures

const val KEY_ATTENDANCE = "attendance_key"

class LectureActivity : AppCompatActivity() {
    private val TAG = "LectureActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Lecture"
        setContentView(R.layout.activity_lecture)
        val lecturePosition = intent?.extras?.getString("lecturePosition").toString().toInt()
        val lecture = Datasource.lectures[lecturePosition]
        val url = Uri.parse(lecture.recordingLink)
        findViewById<Button>(R.id.lecture_link_button).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,url)
            this.startActivity(intent)
        }
        val recyclerView: RecyclerView = findViewById(R.id.quiz_recycler_view)
        val adapter = QuizAdapter(lecture.questions)
        Log.d(TAG,"Lecture questions: ${lecture.questions}")
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.quiz_button).setOnClickListener {
            val score = adapter.getScore().toDouble()
            val maxScore = lecture.questions.size.toDouble()
            val passPercent = 0.8
            val pass = passPercent <= score/maxScore
            val message = "${score.toInt()}/${maxScore.toInt()}"
            if(pass){
                findViewById<TextView>(R.id.score_textView).text = "You scored $message. You pass!"
                takeAttendance(lecture)
            }else{
                findViewById<TextView>(R.id.score_textView).text = "You scored $message. You failed!"
            }
        }
    }

    private fun takeAttendance(lecture: Lectures){
        var found = false
        while(!found){
            for(attendance in Datasource.attendances){
                if(attendance.lecture == lecture){
                    attendance.attendance = true
                    found = true
                    Log.d(TAG,"Attandance.${attendance.lecture.lectureName}: ${attendance.attendance}")
                }
            }
            if(found){
                Toast.makeText(this,"Attendance taken", Toast.LENGTH_SHORT).show()
            }
        }
    }
}