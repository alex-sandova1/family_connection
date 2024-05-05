package com.example.familyconnection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class Questions : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var db: FirebaseFirestore
    private var currentDay = "Front Desk"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        val Return = findViewById<Button>(R.id.back)
        val buttonPrevious = findViewById<Button>(R.id.button_previous)
        val buttonNext = findViewById<Button>(R.id.button_next)
        val textViewCurrentDay = findViewById<TextView>(R.id.textView_currentDay)

        val departments = listOf("Dinning Room", "Front Desk")

        viewManager = LinearLayoutManager(this)

        recyclerView = findViewById<RecyclerView>(R.id.activities_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }

        Return.setOnClickListener {
            startActivity(Intent(this, main_page::class.java))
        }

        buttonPrevious.setOnClickListener {
            val currentIndex = departments.indexOf(currentDay)
            val newIndex = if (currentIndex > 0) currentIndex - 1 else departments.size - 1
            currentDay = departments[newIndex]
            textViewCurrentDay.text = currentDay
            updateData()
        }

        buttonNext.setOnClickListener {
            val currentIndex = departments.indexOf(currentDay)
            val newIndex = if (currentIndex < departments.size - 1) currentIndex + 1 else 0
            currentDay = departments[newIndex]
            textViewCurrentDay.text = currentDay
            updateData()
        }

        db = FirebaseFirestore.getInstance()
        updateData()
    }

private fun updateData() {
    db.collection("FAQ").document(currentDay)
        .addSnapshotListener { value, error ->
            if (error != null) {
                // Handle the error
                return@addSnapshotListener
            }

            if (value != null && value.data != null) {
                val myDataset = ArrayList<String>()
                val fieldNames = listOf("Q1", "Q2") // Add more question fields if needed
                val fieldvalues = listOf("A1", "A2") // Add more answer fields if needed
                for (i in fieldNames.indices) {
                    val question = value.data!![fieldNames[i]]
                    val answer = value.data!![fieldvalues[i]]
                    if (question != null && answer != null) {
                        myDataset.add("$question")
                        myDataset.add("$answer")
                    }
                }

                viewAdapter = MyAdapter(myDataset)
                recyclerView.adapter = viewAdapter
            }
        }
}}