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
import com.codepath.asynchttpclient.RequestHeaders



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

    private fun getNutritionURL(query: String) {
        val client = AsyncHttpClient()
        val apiKey = "+IZHTq/Xdx5jPwmqHF7nLQ==xxSjUFcjDIERR7MA"
        val url = "https://api.api-ninjas.com/v1/nutrition?query=$query"


        val headers = RequestHeaders()
        headers.put("X-Api-Key", apiKey)

        client.get(url, headers, null, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                json?.jsonArray?.let { jsonArray ->
                    if (jsonArray.length() > 0) {
                        val firstItem = jsonArray.getJSONObject(0)
                        val calories = firstItem.getDouble("calories")
                        Toast.makeText(this@AddMeal, "Calories: $calories", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@AddMeal, "Please enter a different food", Toast.LENGTH_SHORT).show()
                    }
                } ?: run {
                    Toast.makeText(this@AddMeal, "No data available", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(statusCode: Int, headers: Headers?, responseString: String?, throwable: Throwable?) {
                Toast.makeText(this@AddMeal, responseString ?: "Error", Toast.LENGTH_SHORT).show()
            }
        })


    }







//    private fun getNutritionURL(query:String){
//        val client = AsyncHttpClient()
//        var apiKey = "+IZHTq/Xdx5jPwmqHF7nLQ==xxSjUFcjDIERR7MA"
//        val url = "https://api.api-ninjas.com/v1/nutrition?query=$query"
//
//        //Toast.makeText(this,url,Toast.LENGTH_SHORT).show()
//
//        client[url, object : JsonHttpResponseHandler() {
//            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
//                val jsonArray = json.jsonArray
//                if (jsonArray.length() > 0) {
//                    val firstItem = jsonArray.getJSONObject(0)
//                    val calories = firstItem.getDouble("calories") // Get calories value
//                    Toast.makeText(this@AddMeal,"Calories" + calories,Toast.LENGTH_SHORT).show()
//                }
//
//            }
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Headers?,
//                errorResponse: String,
//                throwable: Throwable?
//            ) {
//
//                Toast.makeText(this@AddMeal,errorResponse,Toast.LENGTH_SHORT).show()
//            }
//        }]
//    }

//    private fun sendMealBack() {
//        val mealName = findViewById<EditText>(R.id.mealName).text.toString()
//        getNutritionURL(mealName)
//        val returnIntent = Intent()
//        returnIntent.putExtra("meal_key", mealName)
//        setResult(Activity.RESULT_OK, returnIntent)
//        finish()
//    }
}