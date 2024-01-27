package com.example.nekofitness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nekofitness.DataClasses.Routines
import com.example.nekofitness.R
import com.example.nekofitness.database.NekoDBHelper
import com.example.nekofitness.interfaces.clickListening
import com.example.nekofitness.recyclerViewItems.RoutineClickablesAdapter
import com.example.nekofitness.viewModels.RoutineViewModel
import com.google.android.material.button.MaterialButton


class RoutineFragment : Fragment(), clickListening {
    private lateinit var recyclerView: RecyclerView
    private lateinit var db: NekoDBHelper
    private lateinit var routineList: ArrayList<Routines>
    private lateinit var deleteBtn: MaterialButton
    private val routineViewModel: RoutineViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_routine, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.clickableRoutineRV)
        deleteBtn = view.findViewById(R.id.deleteAllRoutines)
        db = NekoDBHelper(requireContext())
        routineList = db.getRoutines()
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        println(routineList)
        val adapter = RoutineClickablesAdapter(routineList, this)
        recyclerView.adapter = adapter
        deleteBtn.setOnClickListener {
            db.clearRoutines()
            routineList.clear()
            adapter.notifyDataSetChanged()
        }
        routineViewModel.dataList.observe(viewLifecycleOwner, { newDataList ->
            val newRoutineList =  db.getRoutines()
            routineList.clear()
            routineList.addAll(newRoutineList)
            adapter.notifyDataSetChanged()
        })
    }

    //Go to Routine details fragment
    override fun onItemClick(position: Int, name: String) {
        val bundle = Bundle()
        bundle.putString("RoutineName", name)
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val detailFragment = RoutineDetailsFragment()
        detailFragment.arguments = bundle
        fragmentTransaction.replace(R.id.fragmentContainerView, detailFragment)
        fragmentTransaction.addToBackStack(null)  // Optional: Add to back stack for navigation history
        fragmentTransaction.commit()
    }

}