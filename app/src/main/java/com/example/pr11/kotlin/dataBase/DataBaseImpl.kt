package com.example.pr11.kotlin.dataBase

import com.example.pr11.kotlin.student.Student

class DataBaseImpl<T : Student> : DataBase<T> {
    private val _data = mutableListOf<T>()

    override val data: List<T>
        get() = _data.toList()

    override fun add(item: T): Boolean = _data.add(item)

    override fun change(index: Int, item: T): Boolean {
        return if ((_data.size > 0) and (index - 1 in 0 until _data.size)) {
            _data.removeAt(index - 1)
            _data.add(index - 1, item)
            true
        } else {
            false
        }
    }

    override fun delete(index: Int): Boolean {
        return if ((_data.size > 0) and (index - 1 in 0 until _data.size)) {
            _data.removeAt(index - 1)
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