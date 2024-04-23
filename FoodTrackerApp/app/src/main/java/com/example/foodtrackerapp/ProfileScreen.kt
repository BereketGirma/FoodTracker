package com.example.foodtrackerapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ProfileScreen: AppCompatActivity() {
    // Declaring TextViews and FloatingActionButton with lateinit var.
    private lateinit var userNameHolder: TextView
    private lateinit var userAgeHolder: TextView
    private lateinit var exerciseAmountHolder: TextView
    private lateinit var returnButton: ImageButton
    private lateinit var profileSettingButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profilescreen)  // Ensure the layout file is named `profilescreen.xml`
        // Initializing the views
        userNameHolder = findViewById(R.id.userNameHolder)
        userAgeHolder = findViewById(R.id.userAgeHolder)
        exerciseAmountHolder = findViewById(R.id.excerciseAmountHolder)
        returnButton = findViewById(R.id.imageButton2)
        profileSettingButton = findViewById(R.id.returnToSurvey)
        returnButton.setOnClickListener {
            // Handle the click event which is going back to main screen.
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        } // setOnClickListner for floating button
        profileSettingButton.setOnClickListener {
            val intent2 = Intent(this,FirstLaunchScreen::class.java)
            startActivity(intent2)
        }

        // Retrieve user's name and age from SharedPreferences
        val sharedPref = getSharedPreferences("my_preference_file", Context.MODE_PRIVATE)

        val userName = sharedPref.getString("userName_fromSurvey", "Unknown")
        val userAge = sharedPref.getString("userAge", "Unknown")
        val userExerciseWeekly = sharedPref.getString("selectedOption", "Unknown")
        userNameHolder.text = userName
        userAgeHolder.text = userAge
        exerciseAmountHolder.text = userExerciseWeekly

    } // onCreate
} // ProfileScreen