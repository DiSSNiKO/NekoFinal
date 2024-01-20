package com.example.nekofitness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nekofitness.R
import com.example.nekofitness.database.DBViewModel


class RoutineFragment : Fragment() {
    private lateinit var mDBViewModel: DBViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_routine, container, false)


        mDBViewModel = ViewModelProvider(this)[DBViewModel::class.java]
        mDBViewModel.getRoutines.observe(viewLifecycleOwner, Observer { routine ->
//            var routinearray = arrayListOf<Routines>()
        })
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}