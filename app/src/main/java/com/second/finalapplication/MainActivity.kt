package com.second.finalapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private  val database = ProjectDatabase(this)
    private lateinit var usersList: RecyclerView
    private lateinit var adapter: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val menu= findViewById<Button>(R.id.menu)
        menu.setOnClickListener{
            val intent = Intent(applicationContext , HomePage::class.java)
            startActivity(intent)
        }
    }
 /*   listAdapter()

private fun listAdapter() {
    adapter = RecyclerView(database.getItems())

    usersList.adapter = adapter
}

override fun onResume() {
    super.onResume()
//        listAdapter()
}*/
}










