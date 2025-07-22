package com.example.mentorme

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Notifications : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notifications)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ImageView>(R.id.backimg_notifications).setOnClickListener {
            val intent = Intent(this, MainMenuScreen_activity::class.java)
            startActivity(intent)
        }

        val clearAll = findViewById<TextView>(R.id.clear_text)

        clearAll.setOnClickListener {
            // List of all notification layouts and lines to remove
            val idsToRemove = listOf(
                R.id.noti1, R.id.line1,
                R.id.noti2, R.id.line2,
                R.id.noti3, R.id.line3,
                R.id.noti4, R.id.line4,
                R.id.noti5, R.id.line5
            )

            for (id in idsToRemove) {
                val view = findViewById<View>(id)
                view?.visibility = View.GONE
            }
        }

        val noti1 = findViewById<LinearLayout>(R.id.noti1)
        val noti2 = findViewById<LinearLayout>(R.id.noti2)
        val noti3 = findViewById<LinearLayout>(R.id.noti3)
        val noti4 = findViewById<LinearLayout>(R.id.noti4)
        val noti5 = findViewById<LinearLayout>(R.id.noti5)

        val view1 = findViewById<View>(R.id.line1)
        val view2 = findViewById<View>(R.id.line2)
        val view3 = findViewById<View>(R.id.line3)
        val view4 = findViewById<View>(R.id.line4)
        val view5 = findViewById<View>(R.id.line5)

        val cross1 = findViewById<ImageView>(R.id.cross1_noti)
        val cross2 = findViewById<ImageView>(R.id.cross2_noti)
        val cross3 = findViewById<ImageView>(R.id.cross3_noti)
        val cross4 = findViewById<ImageView>(R.id.cross4_noti)
        val cross5 = findViewById<ImageView>(R.id.cross5_noti)

        cross1.setOnClickListener { noti1.visibility = View.GONE; view1.visibility = View.GONE }
        cross2.setOnClickListener { noti2.visibility = View.GONE; view2.visibility = View.GONE }
        cross3.setOnClickListener { noti3.visibility = View.GONE; view3.visibility = View.GONE }
        cross4.setOnClickListener { noti4.visibility = View.GONE; view4.visibility = View.GONE }
        cross5.setOnClickListener { noti5.visibility = View.GONE; view5.visibility = View.GONE }





    }
}