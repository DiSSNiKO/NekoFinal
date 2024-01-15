package com.example.nekofitness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AddRoutineFragment : Fragment() {
    private lateinit var mDBViewModel: DBViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_routine, container, false)

        val itemAdapter = AddExercRVadapter(arrayListOf<ExerciseChoice>())
        val routinesrcview: RecyclerView =view.findViewById(R.id.rvfornewroutine)
        routinesrcview.layoutManager = LinearLayoutManager(context)
        routinesrcview.adapter = itemAdapter
        mDBViewModel = ViewModelProvider(this).get(DBViewModel::class.java)
        mDBViewModel.getExercises.observe(viewLifecycleOwner, Observer { exercise ->
            itemAdapter.setItems(exercise)
        })
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ogo = arrayListOf<ExerciseChoice>(
            ExerciseChoice("Bulgarian split squats", "Lower body", false),
            )
        val addRoutineBtn:Button = view.findViewById(R.id.addroutinebtn)

        addRoutineBtn.setOnClickListener {

        }

    }
}