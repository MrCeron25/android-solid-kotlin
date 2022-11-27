package com.example.pr11.kotlin.factory

import com.example.pr11.kotlin.student.StudentImpl

class SimpleStudentFactory : StudentFactory<StudentImpl> {

//    fun create(props: Map<String, *>): com.example.pr11.kotlin.student.StudentImpl {
//        var student: com.example.pr11.kotlin.student.StudentImpl? = null
//        try {
//            student = com.example.pr11.kotlin.student.StudentImpl()
//            student.surname = props["surname"] as String
//            student.name = props["name"] as String
//            student.patronymic = props["patronymic"] as String
//            student.age = props["age"] as? Int ?: throw ClassCastException("Возраст должен быть целым числом")
//            student.sex = props["sex"] as com.example.pr11.kotlin.enums.Sex
//        } catch (error: ClassCastException) {
//            println("Ошибка создания студента: \n${error.message}")
//        }
//        return student ?: throw NullPointerException("Ошибка зарузки студента")
//    }
//
//            mapOf(
//                "surname" to "Алексеев",
//                "name" to "Артём",
//                "patronymic" to "Алексеев",
//                "age" to 18,
//                "sex" to com.example.pr11.kotlin.enums.Sex.WOMAN
//            )

    override fun create(builder: StudentImpl.() -> Unit) = StudentImpl().apply(builder)

}
