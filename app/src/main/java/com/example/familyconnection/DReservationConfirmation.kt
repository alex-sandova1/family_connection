package com.example.familyconnection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DReservationConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dreservation_confirmation)

        val back = findViewById<Button>(R.id.back)
        val occasionConfirmation = findViewById<TextView>(R.id.occasion_confirmation)
        val dateConfirmation = findViewById<TextView>(R.id.date_confirmation)
        val timeConfirmation = findViewById<TextView>(R.id.time_confirmation)
        val numberOfPeople = findViewById<TextView>(R.id.Number_of_people)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val occasion = intent.getStringExtra("occasion")
        val date = intent.getStringExtra("date_input")
        val time = intent.getStringExtra("time_input")
        val attendees = intent.getStringExtra("attendees_input")

        occasionConfirmation.text = occasion
        dateConfirmation.text = date
        timeConfirmation.text = time
        numberOfPeople.text = attendees

        back.setOnClickListener {
            startActivity(Intent(this, main_page::class.java))
        }

    }
}