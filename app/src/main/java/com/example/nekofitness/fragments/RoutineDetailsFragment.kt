package com.example.nekofitness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.isVisible
import com.example.nekofitness.DataClasses.Exercises
import com.example.nekofitness.DataClasses.Routines
import com.example.nekofitness.R
import com.example.nekofitness.database.NekoDBHelper
import kotlinx.serialization.json.Json
import org.w3c.dom.Text


class RoutineDetailsFragment : Fragment() {
    private lateinit var scrollviu:LinearLayout
    private lateinit var routineExercises: ArrayList<Exercises>
    private lateinit var db: NekoDBHelper
    private lateinit var routineDetailsTitle: TextView
    private lateinit var rout: Routines
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val exorcism = arguments?.getString("RoutineName")
        db = NekoDBHelper(requireContext())
        rout = db.getSpecificRoutine(exorcism)
        routineExercises = Json.decodeFromString(rout.exercises)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_routine_details, container, false)
        scrollviu = view.findViewById(R.id.routineDetailedLayoutScroll)
        routineDetailsTitle = view.findViewById(R.id.routineDetailsTitle)
        routineDetailsTitle.text = rout.name
        createExerciseViews(layoutInflater)
        return view
    }

    fun createExerciseViews(inflater: LayoutInflater){
        for (child in scrollviu.children){
            child.setOnClickListener(null)
        }
        scrollviu.removeAllViews()
        for(exercise in routineExercises){
            val vviiuu = inflater.inflate(R.layout.exerciseview_routinedetails,scrollviu,false)
            vviiuu.findViewById<TextView>(R.id.leExerciseNamae).text = exercise.name
            vviiuu.findViewById<TextView>(R.id.leExerciseType).text = exercise.type
            vviiuu.findViewById<TextView>(R.id.leExerciseDifficulty).text = exercise.difficulty
            val frogis = vviiuu.findViewById<TextView>(R.id.leExerciseInstruct)
            frogis.visibility = View.GONE
            frogis.text = exercise.instructions
            vviiuu.setOnClickListener {
                if(frogis.isVisible){
                    frogis.visibility = View.GONE
                } else {
                    frogis.visibility = View.VISIBLE
                }
            }
            scrollviu.addView(vviiuu)
        }
    }
}