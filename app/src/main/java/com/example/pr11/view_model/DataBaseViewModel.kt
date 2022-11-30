package com.example.pr11.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr11.kotlin.enums.Sex
import com.example.pr11.kotlin.student.StudentImpl
import com.example.pr11.model.StudentImplList
import kotlinx.coroutines.launch

class DataBaseViewModel : ViewModel() {

    private val _studentImplList = StudentImplList.instance

    val dataBaseFlow = _studentImplList.dataBase

    fun add(
        surname: String,
        name: String,
        patronymic: String,
        age: Int,
        sex: Sex,
    ) {
        viewModelScope.launch {
            StudentImplList.instance.add(
                StudentImpl(
                    surname, name, patronymic, age, sex
                )
            )
        }
    }

    fun delete(
        index: Int
    ) {
        viewModelScope.launch {
            StudentImplList.instance.delete(index)
        }
    }

    fun change(
        index: Int, student: StudentImpl
    ) {
        viewModelScope.launch {
            StudentImplList.instance.change(index, student)
        }
    }

    companion object {
        val instance by lazy { DataBaseViewModel() }
    }

}