package com.example.foodtrackerapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.foodtrackerapp.databinding.FoodViewInfoBinding
import okhttp3.Headers

class FoodViewScreen: AppCompatActivity()  {
    private lateinit var binding: FoodViewInfoBinding
    private val rvFood get() = binding.foodRecyclerView

    private lateinit var foodInfoList: MutableList<MutableList<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FoodViewInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodInfoList = mutableListOf()

        val adapter = FoodViewAdapter(foodInfoList)
        rvFood.adapter = adapter
        rvFood.layoutManager = LinearLayoutManager(this@FoodViewScreen)
        rvFood.addItemDecoration(
            DividerItemDecoration(
                this@FoodViewScreen,
                LinearLayoutManager.VERTICAL
            )
        )

        val categoryName = intent.getStringExtra("categoryName")
        if (categoryName != null) {
            getCategories(categoryName)
        }
    }

    private fun getCategories(categoryName: String) {
        val client = AsyncHttpClient()

        client["https://www.themealdb.com/api/json/v1/1/filter.php?c=${categoryName}", object: JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("List Loaded", "$json")

                val foodArray = json.jsonObject.getJSONArray("meals")
                for (i in 0 until foodArray.length()) {
                    var curList: MutableList<String> = mutableListOf()
                    curList.add(foodArray.getJSONObject(i).getString("strMeal"))
                    curList.add(foodArray.getJSONObject(i).getString("strMealThumb"))

                    foodInfoList.add(curList)
                }

                rvFood.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.d("Error", response)
            }
        }]
    }
}