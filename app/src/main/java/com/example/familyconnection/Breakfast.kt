package com.example.familyconnection

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class Breakfast : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_breakfast)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var entree1 = findViewById<TextView>(R.id.entree1).text.toString()
        var entree2 = findViewById<TextView>(R.id.entree2).text.toString()
        var entree3 = findViewById<TextView>(R.id.entree3).text.toString()
        var sides = findViewById<TextView>(R.id.sides).text.toString()
        val db = FirebaseFirestore.getInstance()
        db.collection("Menu").document("Always_available").collection("breakfast")
            .document("entree")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                val menu = ArrayList<String>()
                for (doc in value!!.data!!.entries) {
                        menu.add(doc.toString())
                    }
                entree1 = menu[0]
                entree2 = menu[1]
                entree3 = menu[2]
            }



        val Return = findViewById<Button>(R.id.back)
        Return.setOnClickListener {
            startActivity(Intent(this, main_page::class.java))
        }
        val Reservation = findViewById<Button>(R.id.reservation)
        Reservation.setOnClickListener {
            startActivity(Intent(this, DiningReservations::class.java))
        }
    }

}