package com.example.familyconnection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class salon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_salon)

        val confirm = findViewById<Button>(R.id.confirmation_button)
        val serviceInput = findViewById<EditText>(R.id.service_input)
        val dateInput = findViewById<EditText>(R.id.date_input)
        val timeInput = findViewById<EditText>(R.id.time_input)

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
            val intent = Intent(this, salon_confirmation::class.java)
            intent.putExtra("service_input", serviceInput.text.toString())
            intent.putExtra("date_input", dateInput.text.toString())
            intent.putExtra("time_input", timeInput.text.toString())
            startActivity(intent)
        }
    }
}

