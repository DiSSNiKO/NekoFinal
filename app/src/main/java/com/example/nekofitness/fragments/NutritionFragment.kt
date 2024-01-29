package com.example.nekofitness.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nekofitness.DataClasses.ArchiveFoodMacros
import com.example.nekofitness.R
import com.example.nekofitness.database.NekoDBHelper
import com.example.nekofitness.recyclerViewItems.NutrientLogArchiveAdapter
import com.example.nekofitness.viewModels.ArchiveNutritionViewModel
import com.example.nekofitness.viewModels.NutritionViewModel
import com.example.nekofitness.viewPager.viewPagerAdapter
import com.google.android.material.button.MaterialButton


class NutritionFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var db :NekoDBHelper
    private lateinit var deleteeBtn: MaterialButton
    private lateinit var deleteLogsDialog: Dialog
    private lateinit var exitDialog: MaterialButton
    private lateinit var confirmDelete: MaterialButton
    private lateinit var adapterItems: ArrayList<ArchiveFoodMacros>
    private val archiveNutritionViewModel: ArchiveNutritionViewModel by activityViewModels()

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
        adapterItems = db.getArcMacros()
        val adapter = NutrientLogArchiveAdapter(adapterItems)
        dialogShenanigans()
        recyclerView.adapter = adapter
        archiveNutritionViewModel.dataList.observe(viewLifecycleOwner, { newDataList ->
            adapterItems.clear()
            adapterItems.addAll(db.getArcMacros())
            adapter.notifyDataSetChanged()
        })
        deleteeBtn = view.findViewById(R.id.deletee)
        deleteeBtn.setOnClickListener{
            deleteLogsDialog.show()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            db.clearArcFoodLogs()
            archiveNutritionViewModel.updateDataList(db.getArcMacros())
            deleteLogsDialog.dismiss()
        }
    }
}