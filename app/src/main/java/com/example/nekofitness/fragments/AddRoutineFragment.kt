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
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.nekofitness.DataClasses.Exercises
import com.example.nekofitness.R
import com.example.nekofitness.RetrofitAPI.RetrofitInstance
import com.example.nekofitness.database.DBViewModel
import com.example.nekofitness.database.RoutineTB
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class AddRoutineFragment : Fragment() {
    private lateinit var mDBViewModel: DBViewModel
    private lateinit var createRoutineBtn: AppCompatButton
    private lateinit var fetchExers: AppCompatButton
    private lateinit var exerciseSearchInput: EditText
    private lateinit var scrollviu: LinearLayout
    var exercises: ArrayList<Exercises> = arrayListOf<Exercises>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_routine, container, false)
        mDBViewModel = ViewModelProvider(this).get(DBViewModel::class.java)
        scrollviu = view.findViewById(R.id.exercisesScrollView)
        createRoutineBtn = view.findViewById(R.id.createRoutineBtn)
        exerciseSearchInput = view.findViewById(R.id.exerciseSearchInput)
        fetchExers = view.findViewById(R.id.fetchExersBtn)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fetchExers.setOnClickListener {
            Toast.makeText(requireContext(),"womp womp", Toast.LENGTH_SHORT).show()
            fetchExerciseDataAndCreateViews(layoutInflater)
        }
        createRoutineBtn.setOnClickListener {
        }
    }

    private fun insertRoutineToDB(name: EditText,exercises: String) {

    }
    fun fetchExerciseDataAndCreateViews(inflater: LayoutInflater) {
         viewLifecycleOwner.lifecycleScope.launch {
            val response = try {
                RetrofitInstance.api.fetchExercises(exerciseSearchInput.text.toString().toLowerCase())
            } catch (e:IOException){
                Toast.makeText(requireContext(),"Error, check internet connection", Toast.LENGTH_SHORT).show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(requireContext(),"Bad response", Toast.LENGTH_SHORT).show()
                return@launch
            }
            println(response.body())
            if(response.isSuccessful && response.body() != null){
                println("Here are yours trulys --> \n")
                exercises = response.body()!!
                println(exercises)
                createExerciseViews(inflater)
            }
        }
    }
        fun createExerciseViews(inflater: LayoutInflater){
            scrollviu.removeAllViews()
           for(exercise in exercises){
               val vviiuu = inflater.inflate(R.layout.exerciseview,scrollviu,false)
               vviiuu.findViewById<TextView>(R.id.leExerciseNamae).text = exercise.name
               vviiuu.findViewById<TextView>(R.id.leExerciseType).text = exercise.type
               vviiuu.findViewById<TextView>(R.id.leExerciseDifficulty).text = exercise.difficulty
               scrollviu.addView(vviiuu)
           }
        }
}

