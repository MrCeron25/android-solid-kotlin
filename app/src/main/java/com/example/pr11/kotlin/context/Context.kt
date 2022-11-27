package com.example.pr11.kotlin.context

import com.example.pr11.kotlin.dataBase.DataBaseImpl
import com.example.pr11.kotlin.dataBase.PrintStudentDataBase
import com.example.pr11.kotlin.factory.SimpleStudentFactory
import com.example.pr11.kotlin.student.StudentImpl

interface Context {
    val dataBase: DataBaseImpl<StudentImpl>
    val studentFactory: SimpleStudentFactory
    val printStudentDataBase: PrintStudentDataBase<StudentImpl>
}