package com.example.pr11.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pr11.R
import com.example.pr11.databinding.FragmentAddBinding
import com.example.pr11.kotlin.parsers.SexParserImpl
import com.example.pr11.view_model.BaseViewModel
import kotlinx.coroutines.launch


class AddFragment : Fragment(R.layout.fragment_add), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentAddBinding
    private val viewModel: BaseViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddBinding.bind(view)
        addSpinnerSexItems(view, binding.sexSpinner)

        binding.addButton.setOnClickListener {
            lifecycleScope.launch {
                val surname = binding.editTextSurname.text.toString()
                val name = binding.editTextName.text.toString()
                val patronymic = binding.editTextPatronymic.text.toString()
                val age = binding.editTextAge.text.toString().toIntOrNull() ?: 0
                val sex = SexParserImpl().getSex(
                    view, binding.sexSpinner.selectedItem.toString()
                )
                viewModel.dataBaseViewModel.add(surname, name, patronymic, age, sex)
//                Log.d(
//                    "setOnClickListener",
//                    BaseViewModel.instance.dataBaseViewModel.dataBaseFlow.value.toString()
//                )
                findNavController().popBackStack()
            }
        }
    }

    private fun addSpinnerSexItems(view: View, spinner: Spinner) {
        val spinnerArray = listOf(
            getString(R.string.male), getString(R.string.woman)
        )
        val adapter =
            ArrayAdapter(view.context, android.R.layout.simple_spinner_item, spinnerArray).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        spinner.adapter = adapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}