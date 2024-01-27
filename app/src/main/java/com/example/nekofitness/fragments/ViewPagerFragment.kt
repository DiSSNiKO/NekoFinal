package com.example.nekofitness.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.nekofitness.R
import com.example.nekofitness.viewPager.viewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class ViewPagerFragment : Fragment() {
    private lateinit var viewPager : ViewPager2
    private lateinit var main_bottom_navigation_view: BottomNavigationView
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

        val adapter = viewPagerAdapter(fragmentList, childFragmentManager,lifecycle)
        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = adapter
        main_bottom_navigation_view = view.findViewById(R.id.main_bottom_navigation_view)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // Called when a new page is selected
                // Perform the update or action you need here
                for (i in 0 until main_bottom_navigation_view.menu.size()) {
                    main_bottom_navigation_view.menu.getItem(i).isChecked = false
                }
                when (position) {
                    0 -> main_bottom_navigation_view.menu.findItem(R.id.menu_routine).isChecked = true
                    1 -> main_bottom_navigation_view.menu.findItem(R.id.menu_addRoutine).isChecked = true
                    2 -> main_bottom_navigation_view.menu.findItem(R.id.menu_info).isChecked = true
                    3 -> main_bottom_navigation_view.menu.findItem(R.id.menu_calories).isChecked = true
                    4 -> main_bottom_navigation_view.menu.findItem(R.id.menu_foodielogs).isChecked = true
                }
            }
        })
        main_bottom_navigation_view.setOnNavigationItemSelectedListener { model ->
            when (model.itemId) {
                R.id.menu_routine -> viewPager.setCurrentItem(0)
                R.id.menu_addRoutine -> viewPager.setCurrentItem(1)
                R.id.menu_info -> viewPager.setCurrentItem(2)
                R.id.menu_calories -> viewPager.setCurrentItem(3)
                R.id.menu_foodielogs -> viewPager.setCurrentItem(4)
            }
            true
        }
        return view
    }
}