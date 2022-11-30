package com.example.pr11.kotlin

import com.example.pr11.kotlin.commandManager.CommandManagerImpl
import com.example.pr11.kotlin.commands.*
import com.example.pr11.kotlin.context.ContextImpl
import com.example.pr11.kotlin.dataBase.DataBaseImpl
import com.example.pr11.kotlin.dataBase.PrintStudentDataBase
import com.example.pr11.kotlin.enums.Sex
import com.example.pr11.kotlin.factory.SimpleStudentFactory
import com.example.pr11.kotlin.processing.CommandProcessingImpl
import com.example.pr11.kotlin.student.StudentImpl

fun main() {
    val studentFactory = SimpleStudentFactory()
    val dataBase = DataBaseImpl<StudentImpl>().apply {
        add(studentFactory.create {
            surname = "Алексеев"
            name = "Артём"
            patronymic = "Анатольевич"
            age = 18
            sex = Sex.WOMAN
        })
        add(studentFactory.create {
            surname = "Сергей"
            name = "Самохин"
            patronymic = "Витальевич"
            age = 91
            sex = Sex.WOMAN
        })
        add(studentFactory.create {
            surname = "Иван"
            name = "Курилин"
            patronymic = "Павлович"
            age = 48
            sex = Sex.MAN
        })
    }
    val printStudentDataBase = PrintStudentDataBase<StudentImpl>()

    val context = ContextImpl(dataBase, studentFactory, printStudentDataBase)

    //region Commands
    val exitCommand = ExitCommand()
    val deleteCommand = DeleteCommand()
    val addCommand = AddCommand()
    val changeCommand = ChangeCommand()
    val printCommand = PrintCommand()
    val sortCommand = SortCommand()
    val searchCommand = SearchCommand()
    //endregion

    val commandManager = CommandManagerImpl<Command>().apply {
        addCommand(exitCommand)
        addCommand(deleteCommand)
        addCommand(addCommand)
        addCommand(changeCommand)
        addCommand(printCommand)
        addCommand(sortCommand)
        addCommand(searchCommand)
    }
    val helpCommand = HelpCommand(commandManager.commands.values.toList())
    commandManager.addCommand(helpCommand)

    val commandProcessing = CommandProcessingImpl(context, commandManager)
    commandProcessing.processing()
}

// между он старт и он ресьюм