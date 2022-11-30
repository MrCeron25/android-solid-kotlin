package com.example.pr11.model

import com.example.pr11.kotlin.dataBase.DataBaseImpl
import com.example.pr11.kotlin.enums.Sex
import com.example.pr11.kotlin.factory.SimpleStudentFactory
import com.example.pr11.kotlin.student.StudentImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StudentDataBaseFlow {

    private val studentFactory: SimpleStudentFactory = SimpleStudentFactory()

    @OptIn(DelicateCoroutinesApi::class)
    private val context = newSingleThreadContext("StudentList") // list working in new thread
    private val _dataBase = DataBaseImpl<StudentImpl>().apply {
        add(studentFactory.create {
            surname = "Алексеев"
            name = "Артём"
            patronymic = "Анатольевич"
            age = 18
            sex = Sex.WOMAN
        })
        add(studentFactory.create {
            surname = "Сергей"
            name = "Самохин"
            patronymic = "Витальевич"
            age = 91
            sex = Sex.WOMAN
        })
        add(studentFactory.create {
            surname = "Иван"
            name = "Курилин"
            patronymic = "Павлович"
            age = 48
            sex = Sex.MAN
        })
    }
    private val _dataBaseListFlow = MutableStateFlow(_dataBase.data.toList())
    val dataBase = _dataBaseListFlow.asStateFlow()

    private suspend fun updateFlow() {
        withContext(context) {
            _dataBaseListFlow.emit(_dataBase.data.map { it })
        }
    }

    suspend fun add(student: StudentImpl) {
        withContext(context) {
            _dataBase.add(student)
            updateFlow()
        }
    }

    suspend fun delete(index: Int) {
        withContext(context) {
            _dataBase.delete(index)
            updateFlow()
        }
    }

    suspend fun change(index: Int, student: StudentImpl) {
        withContext(context) {
            _dataBase.change(index, student)
            updateFlow()
        }
    }

    suspend fun sortBy(comparator: Comparator<in StudentImpl>) {
        withContext(context) {
            _dataBase.sortWith(comparator)
            updateFlow()
        }
    }

    suspend fun search(vararg predicates: (StudentImpl) -> Boolean): List<StudentImpl> {
        val res: List<StudentImpl>
        withContext(context) {
            res = _dataBase.search(*predicates)
            updateFlow()
        }
        return res
    }

    companion object {
        val instance by lazy { StudentDataBaseFlow() } // instance через lazy - so bad, простой конструктор лучше
    }

}