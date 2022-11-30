package com.example.pr11.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pr11.R
import com.example.pr11.databinding.FragmentSortBinding
import com.example.pr11.view_model.BaseViewModel

class SortFragment : Fragment(R.layout.fragment_sort) {

    private lateinit var binding: FragmentSortBinding
    private val viewModel: BaseViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSortBinding.bind(view)

        binding.sortBySurname.setOnClickListener {
            viewModel.dataBaseViewModel.sortBy(compareBy { it.surname })
            findNavController().popBackStack()
        }

        binding.sortByName.setOnClickListener {
            viewModel.dataBaseViewModel.sortBy(compareBy { it.name })
            findNavController().popBackStack()
        }

        binding.sortByPatronymic.setOnClickListener {
            viewModel.dataBaseViewModel.sortBy(compareBy { it.patronymic })
            findNavController().popBackStack()
        }

        binding.sortByAge.setOnClickListener {
            viewModel.dataBaseViewModel.sortBy(compareBy { it.age })
            findNavController().popBackStack()
        }

        binding.sortBySex.setOnClickListener {
            viewModel.dataBaseViewModel.sortBy(compareBy { it.sex })
            findNavController().popBackStack()
        }
    }

}