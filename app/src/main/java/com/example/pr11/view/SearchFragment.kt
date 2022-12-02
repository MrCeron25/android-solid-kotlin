package com.example.pr11.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pr11.R
import com.example.pr11.databinding.FragmentSearchBinding
import com.example.pr11.kotlin.parsers.SexParserImpl
import com.example.pr11.model.SexSpinner
import com.example.pr11.model.SexSpinner.Companion.MALE_INDEX
import com.example.pr11.view_model.BaseViewModel
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: BaseViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)
        SexSpinner().addSpinnerSexItems(view, binding.sexSpinner)
        binding.sexSpinner.setSelection(MALE_INDEX)

        binding.searchButton.setOnClickListener {
            lifecycleScope.launch {
                val surname = binding.editTextSurname.text.toString()
                val name = binding.editTextName.text.toString()
                val patronymic = binding.editTextPatronymic.text.toString()
                val age = binding.editTextAge.text.toString().toIntOrNull()
                val sex =
                    SexParserImpl().getSexFromResourcesName(binding.sexSpinner.selectedItem.toString())
                // booleanVariable = if (booleanMethod()) exp else true;
                // booleanVariable = !booleanMethod() || exp;
                viewModel.dataBaseViewModel.search({
                    if (surname.isNotEmpty()) it.surname.contains(
                        surname, ignoreCase = true
                    ) else true
                    if (name.isNotEmpty()) it.name.contains(name, ignoreCase = true) else true
                    if (patronymic.isNotEmpty()) it.patronymic.contains(
                        patronymic, ignoreCase = true
                    ) else true
                    if (age != null) it.age == age else true
                    it.sex == sex
                })
                // search сохраняет найденные записи в StudentDataBaseFindIndexesFlow
                findNavController().navigate(
                    R.id.action_searchFragment_to_viewFragment
                )
            }
        }
    }

}