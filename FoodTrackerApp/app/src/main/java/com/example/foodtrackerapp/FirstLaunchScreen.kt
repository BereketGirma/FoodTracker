package com.example.foodtrackerapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@SuppressLint("CustomSplashScreen")
class FirstLaunchScreen : AppCompatActivity() {

    private var prevStarted = "no"
    private var clicked:Boolean = false
    // adding lateinit variables to hold name and age to send it in profile screen
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
            var  inputName = findViewById<EditText>(R.id.editTextText)
            var  inputAge = findViewById<EditText>(R.id.editTextNumber)
            var radioGroup = findViewById<RadioGroup>(R.id.radioGroup2)
            var name = inputName.text.toString()
            var age = inputAge.text.toString()
            val selectedOption = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)?.text.toString()
            Log.d("TAG", name)
            // Save name and age to SharedPreferences
            saveUserData(name, age, selectedOption)

            val intent = Intent(this,MainActivity::class.java)
            // message_key by this key we will receive the value, and put the string

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
    private fun saveUserData(name: String, age: String, selectedOption: String ) {
        val sharedPref = getSharedPreferences("my_preference_file", Context.MODE_PRIVATE)

        with(sharedPref.edit()) {
            putString("userName_fromSurvey", name)
            putString("userAge", age)
            putString("selectedOption", selectedOption)
            apply()
        }
    }
}

