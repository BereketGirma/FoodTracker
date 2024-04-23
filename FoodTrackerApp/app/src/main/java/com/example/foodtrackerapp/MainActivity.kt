package com.example.foodtrackerapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var checkProfileButton:Button
    private lateinit var addItemButton: Button

    private var breakfastCalories = 0.0
    private var lunchCalories = 0.0
    private var dinnerCalories = 0.0

    private lateinit var dinnerlist: MutableList<String>
    private lateinit var lunchlist: MutableList<String>
    private lateinit var breakfastlist: MutableList<String>

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MealRecyclerViewAdapter
    private var mealList: MutableList<Meal> = mutableListOf() // Initialize an empty list
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        checkProfileButton = findViewById(R.id.checkingProfile)
        addItemButton = findViewById<Button>(R.id.addButton)

        dinnerlist = mutableListOf()
        lunchlist = mutableListOf()
        breakfastlist = mutableListOf()


        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize Adapter with an empty mealList
        adapter = MealRecyclerViewAdapter(mealList)

        // Set adapter to RecyclerView
        recyclerView.adapter = adapter

        // Notify adapter that data set has changed
        adapter.notifyDataSetChanged()
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

        // add temporary button to lead to my activity upon launching
        val toCategoryButton: Button = findViewById(R.id.toCategoriesButton)
        toCategoryButton.setOnClickListener() {
            val intent = Intent(this@MainActivity, FoodCategoryScreen::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        // Retrieve meal details from SharedPreferences
        // Retrieve meal details from SharedPreferences
        val sharedPrefs = getSharedPreferences("my_preference_file1", Context.MODE_PRIVATE)
        val mealName = sharedPrefs.getString("mealName", "")
        val calories = sharedPrefs.getFloat("calories", 0f)
        val mealTypeString = sharedPrefs.getString("mealType", "") // Retrieve as String

        // Convert mealTypeString to an integer if it's not empty
        val mealType = if (mealTypeString?.isNotEmpty() == true) mealTypeString.toInt() else 0

        breakfastlist.clear()
        lunchlist.clear()
        dinnerlist.clear()

        // Add meal to the appropriate list based on meal type
        when (mealType) {
            1 -> {
                breakfastCalories += calories.toInt()
                breakfastlist.add(mealName ?: "")
            }
            2 -> {
                lunchCalories += calories.toInt()
                lunchlist.add(mealName ?: "")
            }
            3 -> {
                dinnerCalories += calories.toInt()
                dinnerlist.add(mealName ?: "")
            }
        }
        // Update the RecyclerView adapter
        updateRecyclerView()

    }
    private fun updateRecyclerView() {
        // Clear the existing mealList
        mealList.clear()

        // Add new meals to the mealList
        mealList.add(Meal("Breakfast", breakfastCalories, breakfastlist))
        mealList.add(Meal("Lunch", lunchCalories, lunchlist))
        mealList.add(Meal("Dinner", dinnerCalories, dinnerlist))

        // Notify the adapter that the data set has changed
        adapter.notifyDataSetChanged()
    }
}
