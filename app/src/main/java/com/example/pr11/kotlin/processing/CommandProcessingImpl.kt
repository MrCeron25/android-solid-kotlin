package com.example.pr11.kotlin.processing

import com.example.pr11.kotlin.commandManager.CommandManagerImpl
import com.example.pr11.kotlin.commands.Command
import com.example.pr11.kotlin.context.ContextImpl
import com.example.pr11.kotlin.enums.CommandNames
import com.example.pr11.kotlin.exception.CommandExecutionException

class CommandProcessingImpl(
    override val context: ContextImpl,
    override val commandManager: CommandManagerImpl<Command>
) : CommandProcessing {

    override fun processing() {
        commandManager.tryExecute(context, CommandNames.PRINT.split(" ").filter { it.isNotEmpty() })
        commandManager.tryExecute(context, CommandNames.HELP.split(" ").filter { it.isNotEmpty() })
        while (true) {
            val inputResult = readln().trim()
            try {
                commandManager.tryExecute(context, inputResult.split(" ").filter { it.isNotEmpty() })
            } catch (e: CommandExecutionException) {
                if (e.message == null) continue
                println(e.message)
            }
        }
    }
}

