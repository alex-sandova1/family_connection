package com.example.familyconnection

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class main_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_page)
        val activities_page = findViewById<ImageView>(R.id.Activities_Icon)
        val contact_page = findViewById<ImageView>(R.id.contact_logo)
        val dining_page = findViewById<ImageView>(R.id.restaurant_logo)
        val frontDesk_page = findViewById<ImageView>(R.id.Front_desk_logo)
        val profile_page = findViewById<ImageView>(R.id.profile)
        val salon_page = findViewById<ImageView>(R.id.salon_logo)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        activities_page.setOnClickListener{
            startActivity(Intent(this,activities::class.java))
        }
        contact_page.setOnClickListener{
            startActivity(Intent(this,Contact::class.java))
        }
        dining_page.setOnClickListener {
            startActivity(Intent(this,Dining::class.java))
        }
        frontDesk_page.setOnClickListener {
            startActivity(Intent(this,front_desk::class.java))
        }
        profile_page.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }
        salon_page.setOnClickListener {
            startActivity(Intent(this,salon::class.java))
        }


    }
}