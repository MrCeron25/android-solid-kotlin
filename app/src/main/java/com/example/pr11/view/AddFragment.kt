package com.example.pr11.view

import android.os.Bundle
import android.util.Log
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
import com.example.pr11.model.SexSpinner
import com.example.pr11.model.StudentAdapter
import com.example.pr11.view_model.StudentViewModel
import kotlinx.coroutines.*


class AddFragment : Fragment(R.layout.fragment_add), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentAddBinding
    private lateinit var spinner: Spinner
    private val viewModel: StudentViewModel by activityViewModels()
//    private var scope: CoroutineScope? = null

//    override fun onStart() {
//        super.onStart()
//        scope = CoroutineScope(Dispatchers.Main)
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        scope?.cancel()
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddBinding.bind(view)
        spinner = view.findViewById(R.id.sexSpinner)

        addSpinnerItems(view)

        binding.addButton.setOnClickListener {
            lifecycleScope.launch {
                val surname = binding.editTextSurname.text.toString()
                val name = binding.editTextName.text.toString()
                val patronymic = binding.editTextPatronymic.text.toString()
                val age = binding.editTextAge.text.toString().toIntOrNull() ?: 0
                val sex = SexSpinner.convertToSex(
                    binding.sexSpinner.selectedItem.toString(), this@AddFragment
                )
                viewModel.add(surname, name, patronymic, age, sex)
                Log.d("setOnClickListener", viewModel.studentListFlow.value.toString())
                findNavController().popBackStack()
            }
        }
    }

    private fun addSpinnerItems(view: View) {
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