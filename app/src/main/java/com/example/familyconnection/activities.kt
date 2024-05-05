package com.example.familyconnection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class activities : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var db: FirebaseFirestore
    private var currentDay = "Monday"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        val Return = findViewById<Button>(R.id.back)
        val buttonPrevious = findViewById<Button>(R.id.button_previous)
        val buttonNext = findViewById<Button>(R.id.button_next)
        val textViewCurrentDay = findViewById<TextView>(R.id.textView_currentDay)

        val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        viewManager = LinearLayoutManager(this)

        recyclerView = findViewById<RecyclerView>(R.id.activities_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }

        Return.setOnClickListener {
            startActivity(Intent(this, main_page::class.java))
        }

        buttonPrevious.setOnClickListener {
            val currentIndex = daysOfWeek.indexOf(currentDay)
            val newIndex = if (currentIndex > 0) currentIndex - 1 else daysOfWeek.size - 1
            currentDay = daysOfWeek[newIndex]
            textViewCurrentDay.text = currentDay
            updateData()
        }

        buttonNext.setOnClickListener {
            val currentIndex = daysOfWeek.indexOf(currentDay)
            val newIndex = if (currentIndex < daysOfWeek.size - 1) currentIndex + 1 else 0
            currentDay = daysOfWeek[newIndex]
            textViewCurrentDay.text = currentDay
            updateData()
        }

        db = FirebaseFirestore.getInstance()
        updateData()
    }

    private fun updateData() {
        db.collection("Activities").document(currentDay)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    // Handle the error
                    return@addSnapshotListener
                }

                if (value != null && value.data != null) {
                    val myDataset = ArrayList<String>()
                    val fieldNames = listOf("1st", "2nd", "3rd","4th","5th","6th") // replace with your field names in the order you want
                    for (fieldName in fieldNames) {
                        val fieldValue = value.data!![fieldName]
                        if (fieldValue != null) {
                            myDataset.add("$fieldValue")
                        }
                    }

                    viewAdapter = MyAdapter(myDataset)
                    recyclerView.adapter = viewAdapter
                }
            }
    }
}