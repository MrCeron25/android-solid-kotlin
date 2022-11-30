package com.example.pr11.kotlin.dataBase

import com.example.pr11.kotlin.student.Student

class DataBaseImpl<T : Student> : DataBase<T> {
    private val _data = mutableListOf<T>()

    override val data: List<T>
        get() = _data.toList()

    override fun add(item: T): Boolean = _data.add(item)

    override fun change(index: Int, item: T): Boolean {
        return if (index in _data.indices) {
            _data.removeAt(index)
            _data.add(index, item)
            true
        } else {
            false
        }
    }

    override fun delete(index: Int): Boolean {
        return if (index in _data.indices) {
            _data.removeAt(index)
            true
        } else {
            false
        }
    }

    override fun search(vararg predicates: (T) -> Boolean): List<T> =
        _data
            .filterAll(predicates = predicates)


    private fun <T> List<T>.filterAll(fair: Boolean = false, vararg predicates: (T) -> Boolean): List<T> {
        val result = mutableListOf<T>()
        for (elem in this) {
            val pass =
                if (fair) {
                    predicates.any { it(elem) }
                } else {
//                    predicates.all { it(elem).also { println("$it $elem") } }
                    predicates.all { it(elem) }
                }
            if (pass) {
                result.add(elem)
            }
        }
        return result
    }

    override fun sortWith(comparator: Comparator<in T>) = _data.sortWith(comparator)
}