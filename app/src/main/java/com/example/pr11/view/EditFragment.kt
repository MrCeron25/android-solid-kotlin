package com.example.pr11.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pr11.R
import com.example.pr11.databinding.FragmentEditBinding
import com.example.pr11.kotlin.enums.Sex
import com.example.pr11.kotlin.factory.SimpleStudentFactory
import com.example.pr11.kotlin.parsers.SexParserImpl
import com.example.pr11.model.StudentImplList
import com.example.pr11.view_model.BaseViewModel
import kotlinx.coroutines.launch


class EditFragment : Fragment(R.layout.fragment_edit) {

    private lateinit var binding: FragmentEditBinding
    private val viewModel: BaseViewModel by activityViewModels()
    private var position: Int = -1
    private val studentFactory = SimpleStudentFactory()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEditBinding.bind(view)
        addSpinnerSexItems(view, binding.sexSpinner)

        loadArguments()

        binding.editButton.setOnClickListener {
            lifecycleScope.launch {
                val surname = binding.editTextSurname.text.toString()
                val name = binding.editTextName.text.toString()
                val patronymic = binding.editTextPatronymic.text.toString()
                val age = binding.editTextAge.text.toString().toIntOrNull() ?: 0
                val sex = SexParserImpl().getSex(
                    view, binding.sexSpinner.selectedItem.toString()
                )
                val studentImpl = studentFactory.create {
                    this.surname = surname
                    this.name = name
                    this.patronymic = patronymic
                    this.age = age
                    this.sex = sex
                }
                viewModel.dataBaseViewModel.change(position, studentImpl)
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

    private fun loadArguments() {
        position = requireArguments().getInt(POSITION)
        val dataBase = StudentImplList.instance.dataBase.value
        val dataBaseIndices = dataBase.indices
        if (position in dataBaseIndices) {
            binding.editTextSurname.setText(dataBase[position].surname)
            binding.editTextName.setText(dataBase[position].name)
            binding.editTextPatronymic.setText(dataBase[position].patronymic)
            binding.editTextAge.setText(dataBase[position].age.toString())
            binding.sexSpinner.setSelection(
                when (dataBase[position].sex) {
                    Sex.MAN -> MALE_INDEX //viewModel.activityViewModel.getString(R.string.male)
                    Sex.WOMAN -> WOMAN_INDEX
                    Sex.UNDEFINED -> 0
                }
            )
        }
    }


    companion object {
        const val POSITION = "com.example.pr11.view.EditFragment.POSITION"
        const val MALE_INDEX = 0
        const val WOMAN_INDEX = 1
    }

}