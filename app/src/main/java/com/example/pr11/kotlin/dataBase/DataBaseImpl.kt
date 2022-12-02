package com.example.pr11.kotlin.dataBase

class DataBaseImpl<T> : DataBase<T> {
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
        _data.filterAll(predicates = predicates)


    private fun <T> List<T>.filterAll(
        fair: Boolean = false, vararg predicates: (T) -> Boolean
    ): List<T> {
        val result = mutableListOf<T>()
        for (elem in this) {
            val pass = if (fair) {
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

    fun searchIndexes(vararg predicates: (T) -> Boolean): List<Pair<Int, Int>> =
        _data.indexFilterAll(predicates = predicates)

    private fun <T> List<T>.indexFilterAll(
        vararg predicates: (T) -> Boolean, fair: Boolean = false
    ): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        var counter = 0
        this.forEachIndexed { index, element ->
            val pass = if (fair) {
                predicates.any { it(element) }
            } else {
//                    predicates.all { it(elem).also { println("$it $elem") } }
                predicates.all { it(element) }
            }
            if (pass) {
                result.add(counter to index)
                counter++
            }
        }
        return result
    }

    override fun sortWith(comparator: Comparator<in T>) = _data.sortWith(comparator)

    override fun clear() {
        _data.clear()
    }
}
