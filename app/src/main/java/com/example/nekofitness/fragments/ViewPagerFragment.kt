package com.example.nekofitness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.nekofitness.R
import com.example.nekofitness.viewPager.viewPagerAdapter


class ViewPagerFragment : Fragment() {
    private lateinit var viewPager : ViewPager2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val fragmentList = arrayListOf<Fragment>(
            RoutineFragment(),
            AddRoutineFragment(),
            StatsFragment(),
            CalorieFragment(),
            NutritionFragment()
        )

        val adapter = viewPagerAdapter(fragmentList, requireActivity().supportFragmentManager,lifecycle)
        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = adapter

        return view
    }
}