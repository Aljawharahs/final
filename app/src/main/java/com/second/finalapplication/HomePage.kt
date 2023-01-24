package com.second.finalapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val brekfast = findViewById<Button>(R.id.breakfast)
        val dinner = findViewById<Button>(R.id.lunch)

        brekfast.setOnClickListener {
            val intent = Intent(applicationContext, Menu::class.java)
            startActivity(intent)
        }
        dinner.setOnClickListener {
            val intent = Intent(applicationContext, Menu::class.java)
            startActivity(intent)
        }
    }
}