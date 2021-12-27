package com.example.assignment.data

import android.util.Log
import com.example.assignment.model.Attendance
import com.example.assignment.model.Lectures
import com.example.assignment.model.Questions
import com.example.assignment.model.User
import com.google.firebase.firestore.FirebaseFirestore

class Datasource {
    val db = FirebaseFirestore.getInstance()
    private val TAG = "Datasource"

    companion object Data {
        val question = Questions(
            "testQuestion",
            "testOption_1",
            "testOption_2",
            "testOption_3",
            "testOption_4",
            "testAnswer"
        )
        var questions: MutableList<Questions> = mutableListOf(question)
        var lectures: MutableList<Lectures> = mutableListOf(
            Lectures("testName", "testRecordingLink", questions)
        )
        var attendances: MutableList<Attendance> = mutableListOf(
            Attendance(lectures[0], false)
        )
        val users = mutableListOf(
            User("admin","admin"),
            User("123","123")
        )
    }

    fun data(){
        saveData()
    }

    private fun saveData(){
//first lecture
        val db = FirebaseFirestore.getInstance()
        val lecture: MutableMap<String, Any> = HashMap()
        lecture["lectureName"] = "Cat 1"
        lecture["recordingLink"] = "https://www.youtube.com/watch?v=uHKfrz65KSU"
        // Add a new document with a generated ID
        db.document("lectures/0")
            .set(lecture)
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document cat 1", e) }

        val question: MutableMap<String, Any> = HashMap()
        question["question"] = "cat is cute."
        question["option_1"] = "yes"
        question["option_2"] = "no"
        question["option_3"] = "not sure"
        question["option_4"] = "don't care"
        question["answer"] = "yes"
        db.document("lectures/0/questions/0")
            .set(question)
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document cat 1 question 1", e) }
        question["question"] = "cats are the best."
        question["option_1"] = "yes"
        question["option_2"] = "no"
        question["option_3"] = "not sure"
        question["option_4"] = "don't care"
        question["answer"] = "yes"
        db.document("lectures/0/questions/1")
            .set(question)
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document cat 1 question 2", e) }
        question["question"] = "first cat in the video is standing."
        question["option_1"] = "yes"
        question["option_2"] = "no"
        question["option_3"] = "not sure"
        question["option_4"] = "don't care"
        question["answer"] = "yes"
        db.document("lectures/0/questions/2")
            .set(question)
            .addOnSuccessListener {
                Log.d(TAG,"DocumentSnapshot added")
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document cat 1 question 3", e) }
//second lecture
        lecture["lectureName"] = "Dog 1"
        lecture["recordingLink"] = "https://www.youtube.com/watch?v=pvws6jJsH9U"
        // Add a new document with a generated ID
        db.document("lectures/1")
            .set(lecture)
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document dog 1", e) }
        question["question"] = "dog is cute."
        question["option_1"] = "yes"
        question["option_2"] = "no"
        question["option_3"] = "not sure"
        question["option_4"] = "don't care"
        question["answer"] = "yes"
        db.document("lectures/1/questions/0")
            .set(question)
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document dog 1 question 1", e) }
        question["question"] = "dog is smart."
        question["option_1"] = "yes"
        question["option_2"] = "no"
        question["option_3"] = "not sure"
        question["option_4"] = "don't care"
        question["answer"] = "yes"
        db.document("lectures/1/questions/1")
            .set(question)
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document dog 1 question 2", e) }
        question["question"] = "dog in the video hates baths."
        question["option_1"] = "yes"
        question["option_2"] = "no"
        question["option_3"] = "not sure"
        question["option_4"] = "don't care"
        question["answer"] = "yes"
        db.document("lectures/1/questions/2")
            .set(question)
            .addOnSuccessListener {
                Log.d(TAG,"All DocumentSnapshots added")
                loadData()
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document dog 1 question 3", e) }
    }

    private fun loadData(){
        Datasource().loadQuestions().also {
            Datasource().loadLectures()
        }
    }

    private fun loadQuestions() {
        var count = 0
        questions.clear()
        db.collection("lectures/0/questions").addSnapshotListener { querySnapshot, _ ->
            if (querySnapshot != null) {
                val documentSnapshots = querySnapshot.documents
                for (document in documentSnapshots) {
                    val question = document.get("question").toString()
                    val option_1 = document.get("option_1").toString()
                    val option_2 = document.get("option_2").toString()
                    val option_3 = document.get("option_3").toString()
                    val option_4 = document.get("option_4").toString()
                    val answer = document.get("answer").toString()
                    Log.d(
                        TAG,
                        "Question $count: $question, option1: $option_1, option2: $option_2. Option3: $option_3, Option4: $option_4, Answer: $answer"
                    )
                    val aQuestion =
                        Questions(question, option_1, option_2, option_3, option_4, answer)
                    if (questions.add(aQuestion)) {
                        Log.d(TAG, "questions added to list")
                    }
                }
                if (questions.size == 3) {
                    count++
                } else {
                    Log.d(TAG, "3 questions in lecture 0 not added properly")
                }
            } else {
                Log.d(TAG, "questions collection in lecture $count is empty!")
            }
        }.also {
            db.collection("lectures/1/questions").addSnapshotListener { querySnapshot, _ ->
                if (querySnapshot != null) {
                    val documentSnapshots = querySnapshot.documents
                    for (document in documentSnapshots) {
                        val question = document.get("question").toString()
                        val option_1 = document.get("option_1").toString()
                        val option_2 = document.get("option_2").toString()
                        val option_3 = document.get("option_3").toString()
                        val option_4 = document.get("option_4").toString()
                        val answer = document.get("answer").toString()
                        Log.d(
                            TAG,
                            "Question $count: $question, option1: $option_1, option2: $option_2. Option3: $option_3, Option4: $option_4, Answer: $answer"
                        )
                        val aQuestion =
                            Questions(question, option_1, option_2, option_3, option_4, answer)
                        if (questions.add(aQuestion)) {
                            Log.d(TAG, "questions added to list")
                        }
                    }
                    if (questions.size == 6) {
                        count++
                    } else {
                        Log.d(TAG, "3 questions in lecture 1 not added properly")
                    }
                } else {
                    Log.d(TAG, "questions collection in lecture $count is empty!")
                }
            }
        }
//            .also {
//            if(count==2){
//                Log.d(TAG,"Total questions: ${questions.size}")
//                Log.d(TAG,"Question 1: ${questions[0]}")
//                //Log.d(TAG,"Question 4: ${questions[3]}")
//            }
//        }
    }

    private fun loadLectures() {
        var count = 0
        db.collection("lectures").addSnapshotListener { querySnapshot, error ->
            if (querySnapshot != null) {
                val documentSnapshots = querySnapshot.documents
                lectures.clear()
                for (document in documentSnapshots) {
                    val lectureName = document.get("lectureName").toString()
                    val recordingLink = document.get("recordingLink").toString()
                    val qList =
                        mutableListOf(questions[count], questions[count + 1], questions[count + 2])
                    val aLecture = Lectures(lectureName, recordingLink, qList)
                    lectures.add(aLecture)
                    Log.d(TAG, "Lecture: $lectureName, $recordingLink, $qList")
                    Log.d(TAG, "lecture added to list")
                    count += 3
//                    Log.d(TAG,"Count: $count")
                }
                loadAttendance()
            } else {
                Log.d(TAG, "Lectures failed to load: $error")
            }
        }
    }

    private fun loadAttendance() {
        attendances.clear()
        lectures.forEach{lecture->
            val anAttendance = Attendance(lecture, false)
            Log.d(TAG, "anAttendance: $anAttendance")
            attendances.add(anAttendance)
            Log.d(TAG, "attendance added")
        }
        Log.d(TAG, "total attendance objects: ${attendances.size}")
    }
}