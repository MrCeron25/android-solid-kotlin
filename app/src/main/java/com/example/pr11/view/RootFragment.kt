package com.example.pr11.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pr11.R
import com.example.pr11.databinding.FragmentRootBinding
import kotlin.system.exitProcess

class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding: FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRootBinding.bind(view)

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_rootFragment_to_addFragment)
        }

        binding.sortButton.setOnClickListener {

        }

        binding.searchButton.setOnClickListener {

        }

        binding.exitButton.setOnClickListener {
            exitProcess(0)
        }
    }

}