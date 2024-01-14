package com.example.nekofitness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddRoutineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRoutineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_routine, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ogo = arrayListOf<Exercise>(
            Exercise("Bulgarian split squats", "Lower body"),
            Exercise("Pull overs", "Upper body"),
            Exercise("Preacher curls", "Lower body"),
            Exercise("Deadlift", "Upper body"),
            Exercise("Front lift", "Upper body"),
            Exercise("Flies", "Lower body"),
            Exercise("GAL lift", "Upper body"),
            Exercise("Crunches", "Upper body")
        )
        val itemAdapter = AddExercRVadapter(ogo)
        val routinesrcview: RecyclerView =view.findViewById(R.id.rvfornewroutine)
        routinesrcview.layoutManager = LinearLayoutManager(context)
        routinesrcview.adapter = itemAdapter
    }
}