package com.example.pr11.kotlin.commands

import com.example.pr11.kotlin.context.Context
import com.example.pr11.kotlin.enums.CommandNames
import com.example.pr11.kotlin.parsers.IntParserImpl
import com.example.pr11.kotlin.parsers.Parser

class DeleteCommand(
    override val name: String = CommandNames.DELETE,
    override val description: String = "Команда удаления",
    override val example: String = "${CommandNames.DELETE} deleteIndex",
    override val neededNumberArgs: Int = 1,
    private val intParser: Parser<String, Int?> = IntParserImpl()
) : Command {
    // /del 1
    override fun execute(context: Context, args: List<String>) {
        if (args.size == neededNumberArgs) {
            val (_index) = args
            val resIndex = intParser.parse(_index)
            if (resIndex != null) {
                if (context.dataBase.delete(resIndex)) {
                    println("Студент №$resIndex удалён.")
                } else {
                    println("Ошибка удаления.")
                }
            } else {
                println("Ошибка ввода номера записи.")
            }
        } else {
            println("Ошибка параметров. Пример $example.")
        }
    }
}