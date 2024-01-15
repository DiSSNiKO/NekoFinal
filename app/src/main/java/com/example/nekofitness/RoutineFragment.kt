package com.example.nekofitness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RoutineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_routine, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var ogo = arrayListOf<Routines>(
            Routines("Single hour arm workout", "60 minutes", "Bicep curls, Forearm curls, Skullcrushers, Preacher curls,  Tricep pulldowns, Hanging"),
            Routines("Crossfit weezy", "30 minutes", "Sprint, Loco-ball balance, Light jog, Pseudo pullups"),
            Routines("Urban Outdoor", "30 minutes", "Pushups, Dips, Leaps, Pike pushups, L hang, Pullups"),
        )
        val routinesAdapter = RoutineRVadapter(ogo)
        val routinesrcview:RecyclerView=view.findViewById(R.id.routinesrcview)
        routinesrcview.layoutManager = LinearLayoutManager(context)
        routinesrcview.adapter = routinesAdapter
    }
}