package com.example.foodtrackerapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(intent != null && intent.extras != null){
            val calories = intent.extras?.getDouble("calories",0.0) // This is the number of calories of the food
            val itemName = intent.extras?.getString("item","") // This is the name of the food entered by the user
            Toast.makeText(this, "Food Name: $itemName", Toast.LENGTH_LONG).show()
        }

    }
}