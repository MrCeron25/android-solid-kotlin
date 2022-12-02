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
import com.example.pr11.databinding.FragmentViewBinding
import com.example.pr11.model.StudentAdapter
import com.example.pr11.model.StudentIndexesAdapter
import com.example.pr11.view_model.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*

class ViewFragment : Fragment(R.layout.fragment_view) {

    private lateinit var binding: FragmentViewBinding
    private val viewModel: BaseViewModel by activityViewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentViewBinding.bind(view)

        val studentAdapter = StudentIndexesAdapter(
            viewModel, findNavController(), viewModel.dataBaseViewModel.findDataBaseFlow
        )
        binding.recyclerView.adapter = studentAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        lifecycleScope.launch {
            viewModel.dataBaseViewModel.findDataBaseFlow.collect {
                studentAdapter.notifyDataSetChanged()
            }
        }

    }

}