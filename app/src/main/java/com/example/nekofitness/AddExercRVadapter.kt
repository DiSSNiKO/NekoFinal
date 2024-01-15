package com.example.nekofitness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class AddExercRVadapter (
    var items : ArrayList<ExerciseChoice>
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
            exercisename.setOnCheckedChangeListener { _, isChecked ->
                items[position].isChecked = isChecked
            }
        }
    }
    fun setItems(newitems: List<ExerciseTB>){
        var dem = arrayListOf<ExerciseChoice>()
        for(i in newitems){
            dem.add(ExerciseChoice(i.name,i.category))
        }
        this.items = dem
        notifyDataSetChanged()
    }
}