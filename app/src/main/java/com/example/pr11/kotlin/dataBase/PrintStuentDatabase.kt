package com.example.pr11.kotlin.dataBase

import com.example.pr11.kotlin.student.Student

class PrintStudentDataBase<T : Student> {
    fun print(dataBase: DataBase<T>) {
        if (dataBase.data.isEmpty()) {
            println("База данных пуста.")
        } else {
            val title = "№ | surname name patronymic age sex"
            val aggregate = "==================================="
//                { _: Char, n: Int -> (0 until n).fold("") { result, _ -> "$result=" } }
            println(aggregate)
            println(title)
            println(aggregate)
            dataBase.data.forEachIndexed { index, obj ->
                println("${index + 1} | $obj")
            }
            println(aggregate)
        }
    }
}