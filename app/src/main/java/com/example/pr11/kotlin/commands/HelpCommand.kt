package com.example.pr11.kotlin.commands

import com.example.pr11.kotlin.context.Context
import com.example.pr11.kotlin.enums.CommandNames

class HelpCommand(
    private val commands: List<Command> = emptyList(),
    override val name: String = CommandNames.HELP,
    override val description: String = "Команда помощи",
    override val example: String = CommandNames.HELP,
    override val neededNumberArgs: Int = 0
) : Command {

    override fun execute(context: Context, args: List<String>) {
        commands.forEach { println(it.getInfo()) }
    }

}