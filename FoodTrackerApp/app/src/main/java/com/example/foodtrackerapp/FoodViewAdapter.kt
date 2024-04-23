package com.example.foodtrackerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FoodViewAdapter(private val foodList: List<List<String>>) : RecyclerView.Adapter<FoodViewAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodImage: ImageView
        val foodName: TextView

        init {
            foodImage = view.findViewById(R.id.foodImage)
            foodName = view.findViewById(R.id.foodName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_view_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.foodName.text = foodList[position][0]

        Glide.with(holder.itemView)
            .load(foodList[position][1])
            .centerCrop()
            .into(holder.foodImage)

        holder.foodImage.setOnClickListener() {
            Toast.makeText(holder.itemView.context, "Category number $position clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}