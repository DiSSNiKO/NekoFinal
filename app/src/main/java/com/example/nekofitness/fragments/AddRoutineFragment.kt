package com.example.nekofitness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nekofitness.R
import com.example.nekofitness.database.DBViewModel
import com.example.nekofitness.database.RoutineTB


class AddRoutineFragment : Fragment() {
    private lateinit var mDBViewModel: DBViewModel

    private lateinit var exerciseLayout: LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_routine, container, false)
        mDBViewModel = ViewModelProvider(this).get(DBViewModel::class.java)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    private fun insertRoutineToDB(name: EditText,exercises: String) {

    }
}