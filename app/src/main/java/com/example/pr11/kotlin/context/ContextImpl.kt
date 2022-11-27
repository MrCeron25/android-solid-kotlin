package com.example.pr11.kotlin.context

import com.example.pr11.kotlin.dataBase.DataBaseImpl
import com.example.pr11.kotlin.dataBase.PrintStudentDataBase
import com.example.pr11.kotlin.factory.SimpleStudentFactory
import com.example.pr11.kotlin.student.StudentImpl

/**
* Контекст - это класс который содержит постоянно использующиеся классы
 */
class ContextImpl(
    override val dataBase: DataBaseImpl<StudentImpl>,
    override val studentFactory: SimpleStudentFactory,
    override val printStudentDataBase: PrintStudentDataBase<StudentImpl>
) : Context
