package com.example.familyconnection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login = findViewById<Button>(R.id.login_button)
        val auth = FirebaseAuth.getInstance()

        login.setOnClickListener {
            val email = findViewById<EditText>(R.id.username_input).text.toString()
            val password = findViewById<EditText>(R.id.password_input).text.toString()
            val errorMessage = findViewById<TextView>(R.id.login_error)

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this, main_page::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    errorMessage.text = "Log in failed"
                    task.exception?.let {
                        Log.d("MainActivity", "signInWithEmail:failure", it)
                    }
                }
            }
        }
    }
}