package com.example.pr11.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr11.kotlin.enums.Sex
import com.example.pr11.kotlin.student.StudentImpl
import com.example.pr11.model.StudentDataBaseFlow
import kotlinx.coroutines.launch

class DataBaseViewModel : ViewModel() {

    private val _studentImplList = StudentDataBaseFlow.instance

    val dataBaseFlow = _studentImplList.dataBase

    fun add(
        surname: String,
        name: String,
        patronymic: String,
        age: Int,
        sex: Sex,
    ) {
        viewModelScope.launch {
            StudentDataBaseFlow.instance.add(
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
            StudentDataBaseFlow.instance.delete(index)
        }
    }

    fun change(
        index: Int, student: StudentImpl
    ) {
        viewModelScope.launch {
            StudentDataBaseFlow.instance.change(index, student)
        }
    }

    fun sortBy(comparator: Comparator<in StudentImpl>) {
        viewModelScope.launch {
            StudentDataBaseFlow.instance.sortBy(comparator)
        }
    }

    companion object {
        val instance by lazy { DataBaseViewModel() }
    }

}