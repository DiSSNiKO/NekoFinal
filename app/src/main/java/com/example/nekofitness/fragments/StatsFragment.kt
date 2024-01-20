package com.example.nekofitness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nekofitness.R
import com.example.nekofitness.database.DBViewModel


class StatsFragment : Fragment() {
    private lateinit var mDBViewModel: DBViewModel
    private lateinit var totalroutines:TextView
    private lateinit var totalupxercises:TextView
    private lateinit var totallowxercises:TextView
    private lateinit var totalexercises:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)
        totalroutines = view.findViewById<TextView>(R.id.totalroutines)
        totalupxercises = view.findViewById<TextView>(R.id.totalupperexercises)
        totallowxercises = view.findViewById<TextView>(R.id.totallowerexercises)
        totalexercises = view.findViewById<TextView>(R.id.totalexercises)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDBViewModel = ViewModelProvider(this).get(DBViewModel::class.java)
        mDBViewModel.getRoutines.observe(viewLifecycleOwner, Observer { routine ->
            if(routine.size>0){
                totalroutines.setText(routine.size.toString())
            }
        })
    }
}