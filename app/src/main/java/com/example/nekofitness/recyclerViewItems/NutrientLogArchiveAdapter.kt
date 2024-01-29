package com.example.nekofitness.recyclerViewItems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nekofitness.DataClasses.ArchiveFoodMacros
import com.example.nekofitness.R

class NutrientLogArchiveAdapter (private val macroArcLog: ArrayList<ArchiveFoodMacros>) : RecyclerView.Adapter<NutrientLogArchiveAdapter.TheViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NutrientLogArchiveAdapter.TheViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.nutrition_log_view_archive, parent, false)
        return TheViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TheViewHolder, position: Int) {
        val currentItem = macroArcLog[position]
        holder.viuFoods.text = currentItem.name_and_amount
        holder.viuFat.text = "Fat: "+currentItem.fat_total_g
        holder.viuCarb.text = "Carbs: "+currentItem.carbohydrates_total_g
        holder.viuCalorie.text = currentItem.calories
        holder.viuFibre.text = "Fiber: "+currentItem.fiber_g
        holder.viuProtein.text = "Protein: "+currentItem.protein_g
        holder.viuDate.text = currentItem.addDate
    }


    override fun getItemCount(): Int {
        return macroArcLog.size
    }

    inner class TheViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viuFoods :TextView =  itemView.findViewById(R.id.foodNamesAndAmounts)
        val viuCalorie :TextView = itemView.findViewById(R.id.calorieTextViu)
        val viuProtein :TextView = itemView.findViewById(R.id.proteinTextViu)
        val viuFat :TextView = itemView.findViewById(R.id.fatTextViu)
        val viuCarb :TextView = itemView.findViewById(R.id.carbTextViu)
        val viuFibre :TextView = itemView.findViewById(R.id.fiberTextViu)
        val viuDate :TextView = itemView.findViewById(R.id.dateTime)
    }

}