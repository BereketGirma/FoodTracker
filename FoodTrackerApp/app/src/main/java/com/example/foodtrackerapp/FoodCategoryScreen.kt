package com.example.foodtrackerapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.foodtrackerapp.databinding.FoodCategoryInfoBinding
import okhttp3.Headers

class FoodCategoryScreen: AppCompatActivity()  {
    private lateinit var binding: FoodCategoryInfoBinding
    private val rvCategory get() = binding.foodRecyclerView

    private lateinit var categoryInfoList: MutableList<MutableList<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FoodCategoryInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryInfoList = mutableListOf()

        val adapter = FoodCategoryAdapter(categoryInfoList)
        rvCategory.adapter = adapter
        rvCategory.layoutManager = LinearLayoutManager(this@FoodCategoryScreen)
        rvCategory.addItemDecoration(
            DividerItemDecoration(
                this@FoodCategoryScreen,
                LinearLayoutManager.VERTICAL
            )
        )

        getCategories()
    }

    private fun getCategories() {
        val client = AsyncHttpClient()

        client["https://www.themealdb.com/api/json/v1/1/categories.php", object: JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("List Loaded", "$json")

                val categoryArray = json.jsonObject.getJSONArray("categories")
                for (i in 0 until categoryArray.length()) {
                    var curList: MutableList<String> = mutableListOf()
                    curList.add(categoryArray.getJSONObject(i).getString("strCategory"))
                    curList.add(categoryArray.getJSONObject(i).getString("strCategoryDescription"))
                    curList.add(categoryArray.getJSONObject(i).getString("strCategoryThumb"))

                    categoryInfoList.add(curList)
                }

                rvCategory.adapter?.notifyDataSetChanged()
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