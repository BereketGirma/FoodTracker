package com.example.foodtrackerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FoodCategoryAdapter(private val categoryList: List<List<String>>) : RecyclerView.Adapter<FoodCategoryAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodCategoryImage: ImageView
        val foodCategoryTitle: TextView
        val foodCategoryDescription: TextView

        init {
            foodCategoryImage = view.findViewById(R.id.foodCategoryImage)
            foodCategoryTitle = view.findViewById(R.id.foodCategoryTitle)
            foodCategoryDescription = view.findViewById(R.id.foodCategoryDescription)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_category_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.foodCategoryTitle.text = categoryList[position][0]
        holder.foodCategoryDescription.text = categoryList[position][1]

        Glide.with(holder.itemView)
            .load(categoryList[position][2])
            .centerCrop()
            .into(holder.foodCategoryImage)

        holder.foodCategoryImage.setOnClickListener() {
            Toast.makeText(holder.itemView.context, "Category number $position clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}