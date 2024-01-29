package com.example.nekofitness.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.nekofitness.DataClasses.Exercises
import com.example.nekofitness.DataClasses.Routines
import com.example.nekofitness.R
import com.example.nekofitness.RetrofitAPI.RetrofitInstance
import com.example.nekofitness.database.NekoDBHelper
import com.example.nekofitness.viewModels.RoutineViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException

class AddRoutineFragment : Fragment() {
    private lateinit var  db : NekoDBHelper
    private lateinit var createRoutineBtn: MaterialButton
    private lateinit var fetchExers: MaterialButton
    private lateinit var exerciseSearchInput: EditText
    private lateinit var scrollviu: LinearLayout
    private lateinit var exerciseDialog:Dialog
    private lateinit var dialogClose: MaterialButton
    private lateinit var dialogAdd: MaterialButton
    private lateinit var dialogTitle: TextView
    private lateinit var dialogDesc: TextView
    private lateinit var routineNameInp: EditText
    private lateinit var chosenexerciseDialog:Dialog
    private lateinit var chosendialogClose: MaterialButton
    private lateinit var chosendialogTitle: TextView
    private lateinit var chosenExercisesTextview: TextView
    private lateinit var chosenExercisesCheck: MaterialButton
    private val routineViewModel: RoutineViewModel by activityViewModels()
    private var fetchedExercises: ArrayList<Exercises> = arrayListOf<Exercises>()
    var chosenExercises: ArrayList<Exercises> = arrayListOf<Exercises>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_routine, container, false)
        createViews(view) //creates all fragment specific views
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fetchExers.setOnClickListener {
            fetchExerciseDataAndCreateViews(layoutInflater)
        }
        createRoutineBtn.setOnClickListener {
            insertRoutineToDB()
        }

        chosenExercisesCheck.setOnClickListener {
            chosenexerciseDialog.show()
        }
    }
    fun createViews(view: View){
        scrollviu = view.findViewById(R.id.exercisesScrollView)
        createRoutineBtn = view.findViewById(R.id.createRoutineBtn)
        exerciseSearchInput = view.findViewById(R.id.exerciseSearchInput)
        fetchExers = view.findViewById(R.id.fetchExersBtn)
        chosenExercisesCheck = view.findViewById(R.id.chosenExercisesCheck)
        routineNameInp = view.findViewById(R.id.routineNameInp)
        db = NekoDBHelper(requireContext())
        dialogShenanigans()
    }

    private fun showExerciseDialog(childView:View){
        val exerciseName = childView.findViewById<TextView>(R.id.leExerciseNamae)
        val exerciseInfo : Exercises = findByNameInFetchedExercises(exerciseName.text.toString(), fetchedExercises)
        dialogDesc.text = exerciseInfo.instructions
        dialogTitle.text = exerciseInfo.name
        exerciseDialog.show()
    }

    private fun findByNameInFetchedExercises(exerciseName: String, exerciseArr: ArrayList<Exercises>): Exercises {
        for(ex in exerciseArr){
            if (ex.name == exerciseName){
                return ex
            }
        }
        return Exercises("nondefined")
    }


    private fun dialogShenanigans() {
        exerciseDialog = Dialog(requireContext())
        exerciseDialog.setContentView(R.layout.exercise_desc_dialog)
        exerciseDialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        exerciseDialog.window?.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.dialogbackground))
        exerciseDialog.setCancelable(false)

        dialogClose = exerciseDialog.findViewById(R.id.closeExerDialog)
        dialogAdd = exerciseDialog.findViewById(R.id.addExerDialog)
        dialogTitle = exerciseDialog.findViewById(R.id.exerciseDialogTitle)
        dialogDesc = exerciseDialog.findViewById(R.id.exerciseDialogInstructions)

        dialogClose.setOnClickListener {
            exerciseDialog.dismiss()
        }

        dialogAdd.setOnClickListener {
            val exername = findByNameInFetchedExercises(dialogTitle.text.toString(),chosenExercises).name
            val uniqueexercises = chosenExercises.size
            if(exername=="nondefined"&&uniqueexercises<=8){
                chosenExercises.add(findByNameInFetchedExercises(dialogTitle.text.toString(),fetchedExercises))
                var textile = ""
                var count = 0
                for (ex in chosenExercises){
                    if(count<=uniqueexercises-1){
                        textile+=(count+1).toString()+") "+ex.name+",\n"
                    } else {
                        textile+=(count+1).toString()+") "+ex.name
                    }
                    count++
                }
                chosenExercisesTextview.text = textile
            } else if (exername!="nondefined"){
                Toast.makeText(requireContext(), "Exercise already chosen in given instance!", Toast.LENGTH_SHORT).show()
            } else if (uniqueexercises>8){
                Toast.makeText(requireContext(), "Exercise amount should not exceed 8!", Toast.LENGTH_SHORT).show()
            }
        }
        //Chosen exercise dialog
        chosenexerciseDialog = Dialog(requireContext())
        chosenexerciseDialog.setContentView(R.layout.chosen_exercises_dialog)
        chosenexerciseDialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        chosenexerciseDialog.window?.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.dialogbackground))
        chosenexerciseDialog.setCancelable(false)

        chosendialogClose = chosenexerciseDialog.findViewById(R.id.closeExerDialog)
        chosendialogTitle = chosenexerciseDialog.findViewById(R.id.exerciseDialogTitle)
        chosenExercisesTextview = chosenexerciseDialog.findViewById(R.id.chosenExercisesTextview)
        chosendialogClose.setOnClickListener {
            chosenexerciseDialog.dismiss()
        }

    }

    private fun insertRoutineToDB() {
        val routineExercises = Json.encodeToString(chosenExercises)
        val routineName = routineNameInp.text.toString()
        db.addRoutine(Routines(routineName,routineExercises))
        val getRoutine = db.getRoutines()
        routineViewModel.updateDataList(getRoutine)
        routineNameInp.clearFocus()
        routineNameInp.setText("")
        chosenExercises = arrayListOf<Exercises>()
        chosenExercisesTextview.text = ""
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
            if(response.isSuccessful && response.body() != null){
                fetchedExercises = response.body()!!
                createExerciseViews(inflater)
            }
        }
    }
    fun createExerciseViews(inflater: LayoutInflater){
        for (child in scrollviu.children){
            child.setOnClickListener(null)
        }
        scrollviu.removeAllViews()
       for(exercise in fetchedExercises){
           val vviiuu = inflater.inflate(R.layout.exerciseview,scrollviu,false)
           vviiuu.findViewById<TextView>(R.id.leExerciseNamae).text = exercise.name
           vviiuu.findViewById<TextView>(R.id.leExerciseType).text = exercise.type
           vviiuu.findViewById<TextView>(R.id.leExerciseDifficulty).text = exercise.difficulty
           vviiuu.setOnClickListener{
               showExerciseDialog(vviiuu)
           }
           scrollviu.addView(vviiuu)
       }
    }
}

