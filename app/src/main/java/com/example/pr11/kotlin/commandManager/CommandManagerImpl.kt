package com.example.pr11.kotlin.commandManager

import com.example.pr11.kotlin.commands.Command
import com.example.pr11.kotlin.context.Context
import com.example.pr11.kotlin.enums.CommandNames
import com.example.pr11.kotlin.exception.CommandExecutionException

class CommandManagerImpl<T : Command>(
    override val commands: MutableMap<String, T> = mutableMapOf()
) : CommandManager<T> {

    override fun addCommand(command: T) {
        if (!commands.containsKey(command.name)) {
            commands[command.name] = command
        } else throw Error("Команда с именем \'${command.name}\' уже есть.")
    }

    override fun tryExecute(context: Context, args: List<String>) {
        val firstArg = args.getOrNull(0) ?: throw CommandExecutionException(null)
        val command = commands[firstArg]
            ?: throw CommandExecutionException("Команда $firstArg не найдена. Введите ${CommandNames.HELP} для справки")
        command.execute(context, args.slice(1 until args.size))
    }
}