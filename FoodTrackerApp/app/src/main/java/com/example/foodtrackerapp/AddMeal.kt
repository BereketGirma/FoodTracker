package com.example.foodtrackerapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddMeal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_meal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.addMealBtn).setOnClickListener {
            sendMealBack()
        }

    }

    private fun sendMealBack() {
        val meal = findViewById<EditText>(R.id.mealName).toString()
        val mealName : String = meal.toString()
        val returnIntent = Intent()
        returnIntent.putExtra("meal_key", mealName)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}