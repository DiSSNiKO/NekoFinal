package com.example.nekofitness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoutineRVadapter (
    val items : ArrayList<Routines>
) : RecyclerView.Adapter<RoutineRVadapter.RoutineViewHolder>() {
    class RoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val routinename : TextView = itemView.findViewById(R.id.routinename)
        val routinediff : TextView = itemView.findViewById(R.id.routinediff)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.routinesrow, parent, false)
        return RoutineViewHolder(view)
    }


    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        holder.apply {
            routinename.text = items[position].name
            routinediff.text = items[position].duration
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }

}