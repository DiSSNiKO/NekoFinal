package com.example.nekofitness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider


class ExerciseAddFragment : Fragment() {
    private lateinit var mExViewModel:DBViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exercise_add, container, false)
        mExViewModel = ViewModelProvider(this).get(DBViewModel::class.java)
        val exerciseAddBtn : Button = view.findViewById(R.id.addexercisebtn)
        val upperbody : RadioButton = view.findViewById(R.id.radio_upperbody)
        val lowerbody : RadioButton = view.findViewById(R.id.radio_lowerbody)
        val exername : EditText = view.findViewById(R.id.exercisenameinput)

        exerciseAddBtn.setOnClickListener {
            insertExerciseToDB(upperbody,lowerbody,exername)
        }
        return view
    }

    private fun insertExerciseToDB(upperbody:RadioButton,lowerbody:RadioButton,name:EditText) {
        var category = "emptistring"
        if(upperbody.isChecked){
            category = "Upper body"
        } else if (lowerbody.isChecked) {
            category = "Lower body"
        }
        if(category!="emptistring"&&name.text.toString().length>0){
            val exerciserow = ExerciseTB(name.text.toString(), category, 0)
            mExViewModel.addExercise(exerciserow)
            Toast.makeText(requireContext(),"Exercise added", Toast.LENGTH_SHORT).show()
        }
    }

}