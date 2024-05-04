package com.example.familyconnection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class front_desk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_front_desk)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val Return = findViewById<Button>(R.id.back)
        val outing = findViewById<ImageView>(R.id.outing_icon)
        val faqs = findViewById<ImageView>(R.id.FAQs_icon)
        Return.setOnClickListener {
            startActivity(Intent(this, main_page::class.java))
        }
        outing.setOnClickListener {
            startActivity(Intent(this, Outings::class.java))
        }
        faqs.setOnClickListener {
            startActivity(Intent(this, Questions::class.java))
        }
    }
}