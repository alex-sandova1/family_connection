package com.example.familyconnection

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Spinner
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class salon : AppCompatActivity() {

    private var serviceInput: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_salon)

        val confirm = findViewById<Button>(R.id.confirmation_button)
        val dateInput = findViewById<EditText>(R.id.date_input)
        val timeInput = findViewById<EditText>(R.id.time_input)
        val services = arrayOf("Extensions", "Dye", "Haircut") // replace with your services
        val serviceSpinner = findViewById<Spinner>(R.id.service_spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, services)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        serviceSpinner.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        serviceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                serviceInput = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do something when nothing is selected
            }
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

            // Set the OK button click listener to dismiss the dialog
            okButton.setOnClickListener {
                dialog.dismiss()
            }

            // Set the dialog properties and show it
            dialog.setTitle("Choose Time")

            val window = dialog.window
            val layoutParams = window?.attributes
            layoutParams?.width = (300 * resources.displayMetrics.density).toInt() // 300dp to pixels
            window?.attributes = layoutParams

            dialog.show()
        }
        confirm.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Your appointment for ${serviceInput} on ${dateInput.text} at ${timeInput.text} has been confirmed.")
                .setPositiveButton(android.R.string.ok, null)
                .show()
        }
    }
}

