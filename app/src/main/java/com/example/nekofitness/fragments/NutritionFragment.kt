package com.example.nekofitness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nekofitness.R
import com.example.nekofitness.RetrofitAPI.RetrofitInstance
import com.example.nekofitness.database.NekoDBHelper
import com.example.nekofitness.recyclerViewItems.NutrientLogArchiveAdapter
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class NutritionFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var db :NekoDBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_nutrition, container, false)
        db = NekoDBHelper(requireContext())
        recyclerView = view.findViewById(R.id.archiveMacrosRV)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        val adapter = NutrientLogArchiveAdapter(db.getArcMacros())
        println(db.getArcMacros())
        recyclerView.adapter = adapter
        return view
    }

}