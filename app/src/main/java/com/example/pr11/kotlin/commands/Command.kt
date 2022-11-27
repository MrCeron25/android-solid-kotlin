package com.example.pr11.kotlin.commands

import com.example.pr11.kotlin.context.Context

interface Command {
    val name: String
    val description: String
    val example: String
    val neededNumberArgs: Int

    fun getInfo(): String = "$name - $description. Пример: \'$example\'."

    fun execute(context: Context, args: List<String> = emptyList())
}