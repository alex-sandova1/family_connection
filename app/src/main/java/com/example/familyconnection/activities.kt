package com.example.familyconnection

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CustomSeekBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatSeekBar(context, attrs, defStyleAttr){

    private val paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val days = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        val dayWidth = width / days.size.toFloat()
        val dayTextSize = 40f
        val dayPaddingTop = paddingTop + dayTextSize
        for((index, day) in days.withIndex()){
            paint.textSize = dayTextSize
            canvas.drawText(day, index * dayWidth+dayWidth/2, dayPaddingTop, paint)
        }
        val hours = listOf("10AM", "11AM", "12PM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM")
        val hourHeight = (height - paddingBottom - paddingTop)/hours.size.toFloat()
        for((index, hour) in hours.withIndex()){
            canvas.drawText(hour, paddingLeft.toFloat(), dayPaddingTop + index * hourHeight + hourHeight / 2, paint)
        }
    }
    init {
        paint.apply{
            color = Color.BLACK
            textSize = 30f
            textAlign = Paint.Align.CENTER
            isAntiAlias = true
        }
    }
}
class activities : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_activities)
        val Return = findViewById<Button>(R.id.back)
        val slider = findViewById<SeekBar>(R.id.slider)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Return.setOnClickListener {
            startActivity(Intent(this, main_page::class.java))
        }
        slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean){

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?){

            }
            override fun onStopTrackingTouch(seekBar: SeekBar?){

            }
        })
    }
}