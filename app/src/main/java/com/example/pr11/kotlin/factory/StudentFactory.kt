package com.example.pr11.kotlin.factory

import com.example.pr11.kotlin.student.Student

interface StudentFactory<T : Student> {
    fun create(builder: T.() -> Unit): T
//    fun create(props: Map<String, *>): T
}