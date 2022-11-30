package com.example.pr11.model

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.pr11.R
import com.example.pr11.view_model.BaseViewModel

class SexSpinner {

    fun addSpinnerSexItems(view: View, spinner: Spinner) {
        val spinnerArray = listOf(
            BaseViewModel.instance.activityViewModel.getString(R.string.male),
            BaseViewModel.instance.activityViewModel.getString(R.string.woman),
            BaseViewModel.instance.activityViewModel.getString(R.string.undefined)
        )
        val adapter =
            ArrayAdapter(view.context, android.R.layout.simple_spinner_item, spinnerArray).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        spinner.adapter = adapter
    }

    companion object {
        const val MALE_INDEX = 0
        const val WOMAN_INDEX = 1
        const val UNDEFINED_INDEX = 2
    }
}