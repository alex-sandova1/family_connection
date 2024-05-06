package com.example.familyconnection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Specials : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_specials)
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
        val monday = findViewById<Button>(R.id.monday)
        monday.setOnClickListener {
            startActivity(Intent(this, Monday::class.java))
        }
        val tuesday = findViewById<Button>(R.id.tuesday)
        tuesday.setOnClickListener {
            startActivity(Intent(this, Tuesday::class.java))
        }
        val wednesday = findViewById<Button>(R.id.wednesday)
        wednesday.setOnClickListener {
            startActivity(Intent(this, Wednesday::class.java))
        }
        val thursday = findViewById<Button>(R.id.thursday)
        thursday.setOnClickListener {
            startActivity(Intent(this, Thursday::class.java))
        }
        val friday = findViewById<Button>(R.id.friday)
        friday.setOnClickListener {
            startActivity(Intent(this, Friday::class.java))
        }
        val saturday = findViewById<Button>(R.id.saturday)
        saturday.setOnClickListener {
            startActivity(Intent(this, Saturday::class.java))
        }
        val sunday = findViewById<Button>(R.id.sunday)
        sunday.setOnClickListener {
            startActivity(Intent(this, Sunday::class.java))
        }
    }
}