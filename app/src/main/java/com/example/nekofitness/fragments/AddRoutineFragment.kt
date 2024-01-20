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
import retrofit2.Response
import java.io.IOException




class AddRoutineFragment : Fragment() {
    private lateinit var mDBViewModel: DBViewModel
    private lateinit var createRoutineBtn: AppCompatButton
    private lateinit var exerciseSearchInput: EditText
    var exercises: ArrayList<Exercises> = arrayListOf<Exercises>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_routine, container, false)
        mDBViewModel = ViewModelProvider(this).get(DBViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createRoutineBtn = view.findViewById(R.id.createRoutineBtn)
        exerciseSearchInput = view.findViewById(R.id.exerciseSearchInput)
        createRoutineBtn.setOnClickListener {
            lifecycleScope.launch {
                val response = try {
                    RetrofitInstance.api.fetchExercises(exerciseSearchInput.text.toString())
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
                    print(response.body())
                    Toast.makeText(requireContext(),"nigas", Toast.LENGTH_SHORT).show()
                    println(exercises)
                }
            }
        }
    }

    private fun insertRoutineToDB(name: EditText,exercises: String) {

    }
}