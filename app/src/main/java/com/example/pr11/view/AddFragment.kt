package com.example.pr11.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.pr11.R
import com.example.pr11.databinding.FragmentAddBinding


class AddFragment : Fragment(R.layout.fragment_add), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentAddBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddBinding.bind(view)

        binding.addButton.setOnClickListener {

        }

        addSpinnerItems(view)
    }

    private fun addSpinnerItems(view: View) {
        val spinnerArray = arrayOf(getString(R.string.male), getString(R.string.woman))
        val adapter =
            ArrayAdapter(view.context, android.R.layout.simple_spinner_item, spinnerArray).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        val spinner = view.findViewById<Spinner>(R.id.sexSpinner)
        spinner.adapter = adapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}