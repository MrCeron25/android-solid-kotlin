package com.example.pr11.kotlin.student

import com.example.pr11.kotlin.enums.Sex
import com.example.pr11.kotlin.student.Student
import kotlinx.coroutines.newSingleThreadContext

data class StudentImpl(
    override var surname: String = "",
    override var name: String = "",
    override var patronymic: String? = "",
    override var age: Int = 0,
    override var sex: Sex = Sex.UNDEFINED
) : Student {

    override fun toString(): String {
        return "$surname $name $patronymic $age $sex"
    }

}