package com.example.familyconnection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login = findViewById<Button>(R.id.login_button)
        val db = FirebaseFirestore.getInstance()

        login.setOnClickListener {
            val email = findViewById<EditText>(R.id.username_input).text.toString()
            val password = findViewById<EditText>(R.id.password_input).text.toString()
            val errorMessage = findViewById<TextView>(R.id.login_error)
            db.collection("Users")
                .whereEqualTo("Email", email)
                .whereEqualTo("Password",password)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if(!querySnapshot.isEmpty){
                        startActivity(Intent(this, main_page::class.java))
                    }else{
                        errorMessage.text = "Log in failed"
                    }
                }
        }
    }
}