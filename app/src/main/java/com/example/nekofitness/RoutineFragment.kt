package com.example.nekofitness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RoutineFragment : Fragment() {
    private lateinit var routinesAdapter: RoutineRVadapter
    private lateinit var mDBViewModel: DBViewModel
    private lateinit var routinesrcview: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_routine, container, false)
        routinesAdapter = RoutineRVadapter(arrayListOf<Routines>())
        routinesrcview=view.findViewById(R.id.routinesrcview)
        routinesrcview.layoutManager = LinearLayoutManager(context)
        routinesrcview.adapter = routinesAdapter

        mDBViewModel = ViewModelProvider(this).get(DBViewModel::class.java)
        mDBViewModel.getRoutines.observe(viewLifecycleOwner, Observer { routine ->
            var routinearray = arrayListOf<Routines>()
            for(i in routine){
               routinearray.add(Routines(i.name,i.duration,i.exercises))
            }
            routinesAdapter.items = routinearray
            routinesAdapter.notifyDataSetChanged()
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}