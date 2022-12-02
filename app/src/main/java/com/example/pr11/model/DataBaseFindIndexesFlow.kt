package com.example.pr11.model

import com.example.pr11.kotlin.dataBase.DataBaseImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DataBaseFindIndexesFlow {
    @OptIn(DelicateCoroutinesApi::class)
    private val context =
        newSingleThreadContext("StudentDataBaseFindIndexesFlow") // list working in new thread

    private val _findDataBaseIndexes = DataBaseImpl<Pair<Int, Int>>()
    private val _findDataBaseIndexesFlow = MutableStateFlow(_findDataBaseIndexes.data.toList())
    val dataBase = _findDataBaseIndexesFlow.asStateFlow()

    private suspend fun updateFlow() {
        _findDataBaseIndexesFlow.emit(_findDataBaseIndexes.data.map { it })
    }

    suspend fun addIndexes(pair: Pair<Int, Int>) {
        withContext(context) {
            _findDataBaseIndexes.add(pair)
            updateFlow()
        }
    }

    suspend fun deleteIndex(index: Int) {
        withContext(context) {
            _findDataBaseIndexes.delete(index)
            updateFlow()
        }
    }

    suspend fun clear() {
        withContext(context) {
            _findDataBaseIndexes.clear()
            updateFlow()
        }
    }

    companion object {
        val instance by lazy { DataBaseFindIndexesFlow() }
    }

}