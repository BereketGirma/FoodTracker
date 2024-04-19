package com.example.foodtrackerapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@SuppressLint("CustomSplashScreen")
class FirstLaunchScreen : AppCompatActivity() {

    private var prevStarted = "yes"
    private var clicked:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        setContentView(R.layout.activity_first_launch_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSubmit = findViewById<Button>(R.id.submit)
        btnSubmit.setOnClickListener{
            clicked = true
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences(getString(R.string.app_name),Context.MODE_PRIVATE)
        if(!sharedPreferences.getBoolean(prevStarted,false)){
            if(clicked){
            val editor = sharedPreferences.edit()
            editor.putBoolean(prevStarted,true)
            editor.apply()
            }
        }
        else{
            moveToMainScreen()
        }
    }
    private fun moveToMainScreen(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}

