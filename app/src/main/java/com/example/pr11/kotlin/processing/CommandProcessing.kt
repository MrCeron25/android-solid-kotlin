package com.example.pr11.kotlin.processing

import com.example.pr11.kotlin.commandManager.CommandManager
import com.example.pr11.kotlin.commands.Command
import com.example.pr11.kotlin.context.ContextImpl

interface CommandProcessing {
    val context: ContextImpl
    val commandManager: CommandManager<Command>
    fun processing()
}