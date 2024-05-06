package com.example.familyconnection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AlwaysAvailable : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_always_available)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val Return = findViewById<Button>(R.id.back)
        Return.setOnClickListener {
            startActivity(Intent(this, main_page::class.java))
        }
        val Reservation = findViewById<Button>(R.id.reservation)
        Reservation.setOnClickListener {
            startActivity(Intent(this, DiningReservations::class.java))
        }
        val breakfast = findViewById<Button>(R.id.breakfast)
        breakfast.setOnClickListener {
            startActivity(Intent(this, Breakfast::class.java))
        }
        val lunch = findViewById<Button>(R.id.lunch)
        lunch.setOnClickListener {
            startActivity(Intent(this, Lunch::class.java))
        }
        val dinner = findViewById<Button>(R.id.dinner)
        dinner.setOnClickListener {
            startActivity(Intent(this, Dinner::class.java))
        }
    }
}