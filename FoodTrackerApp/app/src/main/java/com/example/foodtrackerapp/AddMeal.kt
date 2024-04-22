package com.example.foodtrackerapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import android.widget.Toast
import okhttp3.Headers


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
            getNutritionURL(findViewById<EditText>(R.id.mealName).text.toString())
        }

    }

    private fun getNutritionURL(query:String){
        val client = AsyncHttpClient()
        val url = "https://api.api-ninjas.com/v1/nutrition?query=$query"

        //Toast.makeText(this,url,Toast.LENGTH_SHORT).show()

        client[url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val jsonArray = json.jsonArray
                if (jsonArray.length() > 0) {
                    val firstItem = jsonArray.getJSONObject(0)
                    val calories = firstItem.getDouble("calories") // Get calories value
                    Toast.makeText(this@AddMeal,"Calories" + calories,Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Error", errorResponse)
            }
        }]
    }

//    private fun sendMealBack() {
//        val mealName = findViewById<EditText>(R.id.mealName).text.toString()
//        getNutritionURL(mealName)
//        val returnIntent = Intent()
//        returnIntent.putExtra("meal_key", mealName)
//        setResult(Activity.RESULT_OK, returnIntent)
//        finish()
//    }
}