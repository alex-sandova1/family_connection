package com.example.familyconnection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class salon_confirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_salon_confirmation)

        val back = findViewById<Button>(R.id.back)
        val serviceConfirmation = findViewById<TextView>(R.id.service_confirmation)
        val dateConfirmation = findViewById<TextView>(R.id.date_confirmation)
        val timeConfirmation = findViewById<TextView>(R.id.time_confirmation)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        back.setOnClickListener {
            startActivity(Intent(this, salon::class.java))
        }

        // Retrieve values from intent and set them in TextViews
        val serviceInput = intent.getStringExtra("service_input")
        val dateInput = intent.getStringExtra("date_input")
        val timeInput = intent.getStringExtra("time_input")

        serviceConfirmation.text = serviceInput
        dateConfirmation.text = dateInput
        timeConfirmation.text = timeInput
    }
}