package com.example.pr11.model

import com.example.pr11.kotlin.student.StudentImpl
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

class StudentImplList {

    @OptIn(DelicateCoroutinesApi::class)
    private val context = newSingleThreadContext("StudentList")
    private val list = mutableListOf<StudentImpl>()
    private val _listFlow = MutableStateFlow(list.toList())
    val listFlow = _listFlow.asStateFlow()

    suspend fun add(studentImpl: StudentImpl) {
        withContext(context) {
            list.add(studentImpl)
            _listFlow.emit(list.map { it })
        }
    }

    suspend fun delete(studentImpl: StudentImpl) {
        withContext(context) {
//            list. (studentImpl)
//            _listFlow.emit(list.map { it })
        }
    }

    companion object {
        val instance by lazy { StudentImplList() }
    }

}