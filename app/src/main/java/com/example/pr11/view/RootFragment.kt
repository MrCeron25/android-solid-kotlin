package com.example.pr11.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pr11.R
import com.example.pr11.databinding.FragmentRootBinding
import com.example.pr11.model.StudentAdapter
import com.example.pr11.view_model.BaseViewModel
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding: FragmentRootBinding
    private val viewModel: BaseViewModel by activityViewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRootBinding.bind(view)

        val studentAdapter = StudentAdapter(
            viewModel.dataBaseViewModel.dataBaseFlow, viewModel, findNavController()
        )
        binding.recyclerView.adapter = studentAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        lifecycleScope.launch {
            BaseViewModel.instance.dataBaseViewModel.dataBaseFlow.collect {
                studentAdapter.notifyDataSetChanged()
            }
        }

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_rootFragment_to_addFragment)
        }

        binding.sortButton.setOnClickListener {

        }

        binding.searchButton.setOnClickListener {

        }

        binding.exitButton.setOnClickListener {
            viewModel.activityViewModel.finishActivity()
            exitProcess(0)
        }
    }

}