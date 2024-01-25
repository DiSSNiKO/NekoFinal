package com.example.nekofitness.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nekofitness.R


class NekoArcFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            findNavController().navigate(R.id.action_nekoArcFragment_to_viewPagerFragment)
        }, 2000)
        return inflater.inflate(R.layout.fragment_neko_arc, container, false)
    }


}