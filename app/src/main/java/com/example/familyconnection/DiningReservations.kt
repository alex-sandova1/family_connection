package com.example.familyconnection

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.NumberPicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class DiningReservations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dining_reservations)

        val confirmationButton = findViewById<Button>(R.id.confirmation_button)
        val occasion = findViewById<EditText>(R.id.occasion_input)
        val dateInput = findViewById<EditText>(R.id.date_input)
        val timeInput = findViewById<EditText>(R.id.time_input)
        val attendees = findViewById<EditText>(R.id.AttendeesInput)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val Return = findViewById<Button>(R.id.back)
        Return.setOnClickListener {
            startActivity(Intent(this, main_page::class.java))
        }

        dateInput.isFocusable = false
        dateInput.isFocusableInTouchMode = false
        dateInput.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this,
                { _: DatePicker, year: Int, month: Int, day: Int ->
                    dateInput.setText("$day/${month + 1}/$year")
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        timeInput.isFocusable = false
        timeInput.isFocusableInTouchMode = false
        timeInput.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_time_picker)
            val hourPicker = dialog.findViewById<NumberPicker>(R.id.hour_picker)
            val minutePicker = dialog.findViewById<NumberPicker>(R.id.minute_picker)
            val amPmPicker = dialog.findViewById<NumberPicker>(R.id.am_pm_picker)
            val okButton = dialog.findViewById<Button>(R.id.ok_button)

            // Set the values for the hour picker
            hourPicker.minValue = 1
            hourPicker.maxValue = 12

            // Set the values for the minute picker
            minutePicker.minValue = 0
            minutePicker.maxValue = 1
            minutePicker.displayedValues = arrayOf("00", "30")

            // Set the values for the AM/PM picker
            amPmPicker.minValue = 0
            amPmPicker.maxValue = 1
            amPmPicker.displayedValues = arrayOf("AM", "PM")

            // Set OnValueChangedListener on the pickers
            hourPicker.setOnValueChangedListener { _, _, newVal ->
                timeInput.setText(String.format("%02d:%02d %s", newVal, minutePicker.value * 30, amPmPicker.displayedValues[amPmPicker.value]))
            }
            minutePicker.setOnValueChangedListener { _, _, newVal ->
                timeInput.setText(String.format("%02d:%02d %s", hourPicker.value, newVal * 30, amPmPicker.displayedValues[amPmPicker.value]))
            }
            amPmPicker.setOnValueChangedListener { _, _, newVal ->
                timeInput.setText(String.format("%02d:%02d %s", hourPicker.value, minutePicker.value * 30, amPmPicker.displayedValues[newVal]))
            }


            okButton.setOnClickListener {
                dialog.dismiss()
            }


            dialog.setTitle("Choose Time")

            val window = dialog.window
            val layoutParams = window?.attributes
            layoutParams?.width = (300 * resources.displayMetrics.density).toInt() // 300dp to pixels
            window?.attributes = layoutParams

            dialog.show()
        }

confirmationButton.setOnClickListener {
    val occasionText = occasion.text.toString()
    val date = dateInput.text.toString()
    val time = timeInput.text.toString()
    val attendeesText = attendees.text.toString()

    if (occasionText.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty() && attendeesText.isNotEmpty()) {
        val db = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user != null){
            val userEmail = user.email
            db.collection("Users").whereEqualTo("Email", userEmail).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d("Profile", "${document.id} => ${document.data}")
                        val room = document.getString("Room")
                        Log.d("Room", room.toString())

                        val reservation = hashMapOf(
                            "occasion" to occasionText,
                            "date" to date,
                            "time" to time,
                            "attendees" to attendeesText,
                            "Room" to room
                        )

                        db.collection("Dinning").add(reservation)
                            .addOnSuccessListener { documentReference ->
                                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                                AlertDialog.Builder(this)
                                    .setTitle("Confirmation")
                                    .setMessage("Your reservation for $attendeesText people on $date at $time for $occasionText has been confirmed.")
                                    .setPositiveButton(android.R.string.ok, null)
                                    .show()
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error adding document", e)
                            }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("Profile", "Error getting documents: ", exception)
                }
        }
    } else {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Please fill in all fields.")
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
}}
}