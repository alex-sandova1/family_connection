package com.example.familyconnection

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val db = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()

        val nameInput = findViewById<TextView>(R.id.profile_name)
        val lifestyleInput = findViewById<TextView>(R.id.Lifestyle_input)
        val email = findViewById<TextView>(R.id.Email_text)
        val room_number = findViewById<TextView>(R.id.room_number)
        val diet = findViewById<TextView>(R.id.Diet_input)
        val allergies = findViewById<TextView>(R.id.Allergy_type)
        val allergyLevel = findViewById<TextView>(R.id.Allergy_level)
        val phone = findViewById<TextView>(R.id.Number)
        val fav_act_1 = findViewById<TextView>(R.id.favorite_activity1_input)
        val fav_act_2 = findViewById<TextView>(R.id.favorite_activity2_input)
        val fav_act_3 = findViewById<TextView>(R.id.favorite_activity3_input)


        // Fetch the user data from Firestore
        val user = auth.currentUser
        if (user != null) {
            val userEmail = user.email
           // emailInput.text = userEmail // Set the email to the TextView

            db.collection("Users").whereEqualTo("Email", userEmail).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d("Profile", "${document.id} => ${document.data}")
                        allergies.text = document.getString("Allergy")
                        allergyLevel.text = document.getString("Allergy Level")
                        diet.text = document.getString("Diet")
                        email.text = document.getString("Email")
                        lifestyleInput.text = document.getString("Lifestyle")
                        nameInput.text = document.getString("Name")
                        phone.text = document.getString("Phone Number")
                        room_number.text = document.getString("Room")
                        fav_act_1.text = document.getString("Activity1")
                        fav_act_2.text = document.getString("Activity2")
                        fav_act_3.text = document.getString("Activity3")


                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("Profile", "Error getting documents: ", exception)
                }
        } else {
            Log.d("Profile", "User not logged in")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val Return = findViewById<Button>(R.id.back)
        Return.setOnClickListener{
            startActivity(Intent(this, main_page::class.java))
        }
    }
}