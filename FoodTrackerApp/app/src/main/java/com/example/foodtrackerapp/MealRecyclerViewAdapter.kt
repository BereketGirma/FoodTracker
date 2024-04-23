package com.example.foodtrackerapp

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MealRecyclerViewAdapter(private val mealList: MutableList<Meal>) :
    RecyclerView.Adapter<MealRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mealTimeLabel = itemView.findViewById<TextView>(R.id.bldtextLabel) // name of MealTime
        var mealTimeCalorieLabel =
            itemView.findViewById<TextView>(R.id.bldtotalCalorieLabel) // calorie Total label
        var mealTimeNumCalorieLabel =
            itemView.findViewById<TextView>(R.id.calorienumview) // calorie NUmber Label
        var mealListMessage = itemView.findViewById<TextView>(R.id.listLabel) // list message
        var listEachTimeMeal = itemView.findViewById<TextView>(R.id.listview)
        var imageViewPerTimeMeal = itemView.findViewById<ImageView>(R.id.bldimageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerviewbld, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealList[position]

        // Set meal data based on position
        holder.mealTimeLabel.text = meal.mealTime
        holder.mealTimeCalorieLabel.text = "Calories Total : "
        holder.mealTimeNumCalorieLabel.text = "${meal.totalCalories.toString()}" // You may need to update this based on the total calories
        holder.mealListMessage.text = "item list here : "
        holder.listEachTimeMeal.text = "${meal.itemList}" // You may need to update this based on the item list

        // Load image based on meal time
        val imageResId = when (meal.mealTime) {
            "BREAKFAST" -> R.drawable.breakfast
            "Lunch" -> R.drawable.lunchphoto
            "Dinner" -> R.drawable.dinnerphoto
            else -> R.drawable.breakfast // Add a default image resource ID if needed
        }
        Glide.with(holder.itemView.context)
            .load(imageResId)
            .into(holder.imageViewPerTimeMeal)

            // You can add any additional logic here, such as handling item clicks
        }

        override fun getItemCount() = mealList.size

    fun setMealList(mealTime: String, totalCalories: Double, itemList: MutableList<String>) {
        val meal = Meal(mealTime, totalCalories, itemList)
        when (mealTime) {
            "BREAKFAST" -> mealList[0] = meal
            "Lunch" -> mealList[1] = meal
            "Dinner" -> mealList[2] = meal
        }
        notifyDataSetChanged() // Notify the adapter that the data set has changed
    }

}
data class Meal(
    val mealTime: String,
    val totalCalories: Double,
    val itemList: MutableList<String>
)

