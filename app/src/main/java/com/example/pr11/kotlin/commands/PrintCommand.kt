package com.example.pr11.kotlin.commands

import com.example.pr11.kotlin.commands.Command
import com.example.pr11.kotlin.context.Context
import com.example.pr11.kotlin.enums.CommandNames

class PrintCommand(
    override val name: String = CommandNames.PRINT,
    override val description: String = "Команда вывода",
    override val example: String = CommandNames.PRINT,
    override val neededNumberArgs: Int = 0
) : Command {

    override fun execute(context: Context, args: List<String>) {
        context.printStudentDataBase.print(context.dataBase)
    }

}