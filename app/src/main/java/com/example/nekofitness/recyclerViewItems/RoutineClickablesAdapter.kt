package com.example.nekofitness.recyclerViewItems
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nekofitness.DataClasses.Exercises
import com.example.nekofitness.DataClasses.Routines
import com.example.nekofitness.R
import com.example.nekofitness.interfaces.clickListening
import kotlinx.serialization.json.Json
import com.google.android.material.button.MaterialButton


class RoutineClickablesAdapter (private val clickableRoutines: ArrayList<Routines>,private val listener: clickListening) : RecyclerView.Adapter<RoutineClickablesAdapter.TheViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.routineview, parent, false)
        return TheViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return clickableRoutines.size
    }

    override fun onBindViewHolder(holder: TheViewHolder, position: Int) {
        val currentItem = clickableRoutines[position]
        holder.routineName.text = currentItem.name
        val exers = Json.decodeFromString<ArrayList<Exercises>>(currentItem.exercises)
        holder.routineNumOfExercises.text = exers.size.toString() + " Exercises"

    }

    inner class TheViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val routineName: TextView = itemView.findViewById(R.id.routineClickableName)
        val routineNumOfExercises: TextView =
            itemView.findViewById(R.id.routineClickableExerciseAmount)

        init {
            // Attach the click listener to the itemView or any specific views within it
            itemView.findViewById<MaterialButton>(R.id.viewRoutineBtn).setOnClickListener {
                // Get the adapter position of the clicked item
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position, routineName.text.toString())
                }
            }
        }

    }
}