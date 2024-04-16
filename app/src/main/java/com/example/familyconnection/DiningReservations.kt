package com.example.familyconnection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DiningReservations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dining_reservations)

        val confirm = findViewById<Button>(R.id.confirmation_button)
        val occasion = findViewById<EditText>(R.id.occasion_input)
        val date = findViewById<EditText>(R.id.date_input)
        val time = findViewById<EditText>(R.id.time_input)
        val attendees = findViewById<EditText>(R.id.AttendeesInput)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val Return = findViewById<Button>(R.id.back)
        Return.setOnClickListener {
            startActivity(Intent(this, main_page::class.java))
        }

        confirm.setOnClickListener {
            intent.putExtra("occasion", occasion.text.toString())
            intent.putExtra("date_input", date.text.toString())
            intent.putExtra("time_input", time.text.toString())
            intent.putExtra("attendees_input", attendees.text.toString())
            startActivity(Intent(this, DReservationConfirmation::class.java))
        }
    }
}