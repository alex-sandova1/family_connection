package com.example.familyconnection

import android.content.Intent
import android.media.Image
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
        val profile_page = findViewById<ImageView>(R.id.profile)
        val contact_page = findViewById<ImageView>(R.id.contact_logo)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        profile_page.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }
        contact_page.setOnClickListener{
            startActivity(Intent(this,contact::class.java))
        }

    }
}