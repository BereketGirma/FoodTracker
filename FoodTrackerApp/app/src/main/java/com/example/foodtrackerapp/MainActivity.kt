package com.example.foodtrackerapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var checkProfileButton: Button
    private lateinit var addItemButton: Button
    private lateinit var totalCaloriesTextView: TextView
    private lateinit var totalLeftCaloriesTextView: TextView

    private var breakfastCalories = 0.0
    private var lunchCalories = 0.0
    private var dinnerCalories = 0.0

    private lateinit var dinnerlist: MutableList<String>
    private lateinit var lunchlist: MutableList<String>
    private lateinit var breakfastlist: MutableList<String>

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MealRecyclerViewAdapter
    private var mealList: MutableList<Meal> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        checkProfileButton = findViewById(R.id.checkingProfile)
        addItemButton = findViewById<Button>(R.id.addButton)
        totalCaloriesTextView = findViewById(R.id.textViewFoodTotalCaloriesValue)  // Initialize TextView
        totalLeftCaloriesTextView = findViewById(R.id.textViewTotalLeftCaloriesValue)

        dinnerlist = mutableListOf()
        lunchlist = mutableListOf()
        breakfastlist = mutableListOf()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MealRecyclerViewAdapter(mealList)
        recyclerView.adapter = adapter

        checkProfileButton.setOnClickListener {
            val intent = Intent(this, ProfileScreen::class.java)
            startActivity(intent)
        }
        addItemButton.setOnClickListener {
            val intent = Intent(this, AddMeal::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toCategoryButton: Button = findViewById(R.id.toCategoriesButton)
        toCategoryButton.setOnClickListener() {
            val intent = Intent(this@MainActivity, FoodCategoryScreen::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadDataAndUpdateUI()
    }

    private fun loadDataAndUpdateUI() {
        val sharedPrefs = getSharedPreferences("my_preference_file1", Context.MODE_PRIVATE)
        val mealName = sharedPrefs.getString("mealName", "")
        val calories = sharedPrefs.getFloat("calories", 0f)
        val mealTypeString = sharedPrefs.getString("mealType", "")
        val mealType = mealTypeString?.toIntOrNull() ?: 0

        // Clear existing lists to prepare for new data
        breakfastlist.clear()
        lunchlist.clear()
        dinnerlist.clear()

        when (mealType) {
            1 -> {
                breakfastCalories += calories.toDouble()
                breakfastlist.add(mealName ?: "")
            }
            2 -> {
                lunchCalories += calories.toDouble()
                lunchlist.add(mealName ?: "")
            }
            3 -> {
                dinnerCalories += calories.toDouble()
                dinnerlist.add(mealName ?: "")
            }
        }

        // Update total calories and UI
        updateTotalCalories()
        updateRemainingCalories()
        updateRecyclerView()
    }

    private fun updateTotalCalories() {
        val totalCalories = breakfastCalories + lunchCalories + dinnerCalories
        totalCaloriesTextView.text = String.format("%.1f", totalCalories)
    }

    private fun updateRemainingCalories() {
        val totalCalories = breakfastCalories + lunchCalories + dinnerCalories
        val remainingCalories = 2000 - totalCalories
        totalLeftCaloriesTextView.text = String.format("%.1f", remainingCalories)
    }

    private fun updateRecyclerView() {
        mealList.clear()
        mealList.add(Meal("Breakfast", breakfastCalories, breakfastlist))
        mealList.add(Meal("Lunch", lunchCalories, lunchlist))
        mealList.add(Meal("Dinner", dinnerCalories, dinnerlist))
        adapter.notifyDataSetChanged()
    }
}
