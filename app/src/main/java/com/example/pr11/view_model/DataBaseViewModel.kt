package com.example.pr11.view_model

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr11.kotlin.enums.Sex
import com.example.pr11.kotlin.student.StudentImpl
import com.example.pr11.model.StudentDataBaseFlow
import kotlinx.coroutines.*

class DataBaseViewModel : ViewModel() {

    private val _studentImplList = StudentDataBaseFlow.instance

    val dataBaseFlow = _studentImplList.dataBase

    val findDataBaseFlow = _studentImplList.findDataBase

    fun add(
        surname: String,
        name: String,
        patronymic: String,
        age: Int,
        sex: Sex,
        image: Bitmap? = null,
    ) {
        viewModelScope.launch {
            StudentDataBaseFlow.instance.add(
                StudentImpl(
                    surname, name, patronymic, age, sex, image
                )
            )
        }
    }

    fun addToFindDataBase(
        student: StudentImpl
    ) {
        viewModelScope.launch {
            StudentDataBaseFlow.instance.addToFindDataBase(student)
        }
    }

    fun delete(index: Int) {
        viewModelScope.launch {
            StudentDataBaseFlow.instance.delete(index)
        }
    }

    fun deleteFindDataBase(index: Int) {
        viewModelScope.launch {
            StudentDataBaseFlow.instance.deleteFindDataBase(index)
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

    suspend fun search(vararg predicates: (StudentImpl) -> Boolean): List<StudentImpl> {
        var res: List<StudentImpl> = emptyList()
        viewModelScope.async {
            res = StudentDataBaseFlow.instance.search(*predicates)
        }.await()
        return res
    }

    companion object {
        val instance by lazy { DataBaseViewModel() }
    }

}