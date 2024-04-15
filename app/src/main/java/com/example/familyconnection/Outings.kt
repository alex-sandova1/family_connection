package com.example.familyconnection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Outings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_outings)
        val confirmation = findViewById<Button>(R.id.confirmination_button)
        val back = findViewById<Button>(R.id.back)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        confirmation.setOnClickListener{
            var time = findViewById<EditText>(R.id.time_input)
            startActivity(Intent(this, Outing_confirmation::class.java))
        }
        back.setOnClickListener{
            startActivity(Intent(this, front_desk::class.java))
        }
    }
}