package com.example.pr11.view

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
import com.example.pr11.view_model.StudentViewModel
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding: FragmentRootBinding
//    private val viewModel = BaseViewModel()
private val viewModel: StudentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        val recyclerView = findViewById<RecyclerView>(R.id.electionsListRecyclerView)
//        recyclerView?.layoutManager = LinearLayoutManager(this)
//        viewModel.activityState.createRecycler(this, recyclerView)

        binding = FragmentRootBinding.bind(view)

        val studentAdapter = StudentAdapter(viewModel.studentListFlow)
        binding.recyclerView.adapter = studentAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        lifecycleScope.launch {
            viewModel.studentListFlow.collect {
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
            exitProcess(0)
        }
    }

}