package com.example.pr11.kotlin.commandManager

import com.example.pr11.kotlin.commands.Command
import com.example.pr11.kotlin.context.Context

interface CommandManager<T : Command> {
    fun addCommand(command: T)

    fun tryExecute(context: Context, args: List<String>)

    val commands: MutableMap<String, T>
}