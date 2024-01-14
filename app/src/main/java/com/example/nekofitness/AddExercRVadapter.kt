package com.example.nekofitness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AddExercRVadapter (
    val items : ArrayList<Exercise>
) : RecyclerView.Adapter<AddExercRVadapter.RoutineViewHolder>() {
    class RoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exercisename : CheckBox = itemView.findViewById(R.id.exercheckbox)
        val exercheckboxcategory: TextView = itemView.findViewById(R.id.exercheckboxcategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_checkbox, parent, false)
        return RoutineViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        holder.apply {
            exercisename.text = items[position].exercisename
            exercheckboxcategory.text = items[position].exercisecategory
        }
    }
}