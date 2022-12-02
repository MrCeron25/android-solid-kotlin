package com.example.pr11.view_model

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr11.kotlin.enums.Sex
import com.example.pr11.kotlin.student.StudentImpl
import com.example.pr11.model.DataBaseFindIndexesFlow
import com.example.pr11.model.StudentDataBaseFlow
import kotlinx.coroutines.*

class DataBaseViewModel : ViewModel() {

    private val _studentDataBaseFlow = StudentDataBaseFlow.instance
    val dataBaseFlow = _studentDataBaseFlow.dataBase

    private val _dataBaseFindIndexesFlow = DataBaseFindIndexesFlow.instance
    val findDataBaseFlow = _dataBaseFindIndexesFlow.dataBase

    fun add(
        surname: String,
        name: String,
        patronymic: String,
        age: Int,
        sex: Sex,
        photo: Bitmap? = null,
    ) {
        viewModelScope.launch {
            _studentDataBaseFlow.add(
                StudentImpl(
                    surname, name, patronymic, age, sex, photo
                )
            )
        }
    }

    fun delete(index: Int) {
        viewModelScope.launch {
            _studentDataBaseFlow.delete(index)
        }
    }

    fun deleteIndex(index: Int) {
        viewModelScope.launch {
            _dataBaseFindIndexesFlow.deleteIndex(index)
        }
    }

    fun change(
        index: Int, student: StudentImpl
    ) {
        viewModelScope.launch {
            _studentDataBaseFlow.change(index, student)
        }
    }

    fun sortBy(comparator: Comparator<in StudentImpl>) {
        viewModelScope.launch {
            _studentDataBaseFlow.sortBy(comparator)
        }
    }

    suspend fun search(vararg predicates: (StudentImpl) -> Boolean): List<Pair<Int, Int>> {
        var res: List<Pair<Int, Int>> = emptyList()
        viewModelScope.async {
            res = _studentDataBaseFlow.searchIndexes(*predicates)
            _dataBaseFindIndexesFlow.clear()
            res.forEach {
                _dataBaseFindIndexesFlow.addIndexes(it)
            }
        }.await()
        return res
    }

    companion object {
        val instance by lazy { DataBaseViewModel() }
    }

}