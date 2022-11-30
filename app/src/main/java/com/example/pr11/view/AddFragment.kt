package com.example.pr11.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pr11.R
import com.example.pr11.databinding.FragmentAddBinding
import com.example.pr11.kotlin.parsers.SexParserImpl
import com.example.pr11.model.SexSpinner
import com.example.pr11.view_model.BaseViewModel
import kotlinx.coroutines.launch


class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var binding: FragmentAddBinding
    private val viewModel: BaseViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddBinding.bind(view)
        SexSpinner().addSpinnerSexItems(view, binding.sexSpinner)

        binding.addButton.setOnClickListener {
            lifecycleScope.launch {
                val surname = binding.editTextSurname.text.toString()
                val name = binding.editTextName.text.toString()
                val patronymic = binding.editTextPatronymic.text.toString()
                val age = binding.editTextAge.text.toString().toIntOrNull() ?: 0
                val sex = SexParserImpl().getSexFromResourcesName(binding.sexSpinner.selectedItem.toString())
                viewModel.dataBaseViewModel.add(surname, name, patronymic, age, sex)
                findNavController().popBackStack()
            }
        }
    }

}