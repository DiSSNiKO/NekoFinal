package com.example.nekofitness.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
    private lateinit var adapter:RoutineClickablesAdapter
    private lateinit var deleteLogsDialog: Dialog
    private lateinit var exitDialog: MaterialButton
    private lateinit var confirmDelete: MaterialButton
    private val routineViewModel: RoutineViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_routine, container, false)
        recyclerView = view.findViewById(R.id.clickableRoutineRV)
        deleteBtn = view.findViewById(R.id.deleteAllRoutines)
        db = NekoDBHelper(requireContext())
        routineList = db.getRoutines()
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        dialogShenanigans()
        println(routineList)
        adapter = RoutineClickablesAdapter(routineList, this)
        recyclerView.adapter = adapter
        deleteBtn.setOnClickListener {
            deleteLogsDialog.show()
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

    fun dialogShenanigans() {
        deleteLogsDialog = Dialog(requireContext())
        deleteLogsDialog.setContentView(R.layout.log_deletion_dialog)
        deleteLogsDialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        deleteLogsDialog.window?.setBackgroundDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.dialogbackground
            )
        )
        deleteLogsDialog.setCancelable(false)

        exitDialog = deleteLogsDialog.findViewById(R.id.exitDeletion)
        confirmDelete = deleteLogsDialog.findViewById(R.id.confirmDeletion)

        exitDialog.setOnClickListener {
            deleteLogsDialog.dismiss()
        }

        confirmDelete.setOnClickListener {
            db.clearRoutines()
            routineList.clear()
            adapter.notifyDataSetChanged()
            deleteLogsDialog.dismiss()
        }
    }
}