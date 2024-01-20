package com.example.nekofitness

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


class AddRoutineFragment : Fragment() {
    private lateinit var mDBViewModel: DBViewModel

    private lateinit var exerciseLayout: LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_routine, container, false)
        exerciseLayout = view.findViewById(R.id.layoutforexercises)
        mDBViewModel = ViewModelProvider(this).get(DBViewModel::class.java)
        mDBViewModel.getExercises.observe(viewLifecycleOwner, Observer { exercise ->
            showExercises(exerciseLayout,exercise)
        })
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addRoutineBtn:Button = view.findViewById(R.id.addroutinebtn)
        addRoutineBtn.setOnClickListener {
            var i = 0
            var exerstr = ""
            while (i<exerciseLayout.size){
                if(exerciseLayout[i].findViewById<CheckBox>(R.id.exercheckbox).isChecked){
                    exerstr+=exerciseLayout.get(i).findViewById<CheckBox>(R.id.exercheckbox).text.toString()+", "
                }
                i++
            }
            exerstr = exerstr.removeSuffix(", ")
            insertRoutineToDB(view.findViewById<EditText>(R.id.newroutinename), exerstr)
        }
    }
    fun showExercises(leiaout: LinearLayout, data: List<ExerciseTB>){
        for (i in data){
            val inflater = LayoutInflater.from(requireContext())
            val xmlLayout = inflater.inflate(R.layout.exercise_checkbox, null)
            val checker = xmlLayout.findViewById<CheckBox>(R.id.exercheckbox)
            val textViu = xmlLayout.findViewById<TextView>(R.id.exercheckboxcategory)
            checker.text = i.name
            textViu.text = i.category
            leiaout.addView(xmlLayout)
        }
    }
    private fun insertRoutineToDB(name: EditText,exercises: String) {
        var duration = "Unselectid"
        if(view?.findViewById<RadioButton>(R.id.radio_30min)?.isChecked == true){
            duration = "30 minutes"
        } else if (view?.findViewById<RadioButton>(R.id.radio_onehour)?.isChecked == true){
            duration = "60 minutes"
        }
        if (duration!="Unselectid"&& name.text.toString()!=""&&exercises!=""){
            val rout = RoutineTB(name.text.toString(),duration, exercises, 0)
            mDBViewModel.addRoutine(rout)
            Toast.makeText(requireContext(),"Routine added", Toast.LENGTH_SHORT).show()
        }
    }
}