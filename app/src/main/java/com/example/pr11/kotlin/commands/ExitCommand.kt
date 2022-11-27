package com.example.pr11.kotlin.commands

import com.example.pr11.kotlin.context.Context
import com.example.pr11.kotlin.enums.CommandNames
import kotlin.system.exitProcess

class ExitCommand(
    override val name: String = CommandNames.EXIT,
    override val description: String = "Команда выхода",
    override val example: String = CommandNames.EXIT,
    override val neededNumberArgs: Int = 0
) : Command {
    override fun execute(context: Context, args: List<String>) {
        exitProcess(0)
    }
}