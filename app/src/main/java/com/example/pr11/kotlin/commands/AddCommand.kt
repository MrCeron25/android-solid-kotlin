package com.example.pr11.kotlin.commands

import com.example.pr11.kotlin.context.Context
import com.example.pr11.kotlin.enums.CommandNames
import com.example.pr11.kotlin.enums.Sex
import com.example.pr11.kotlin.parsers.IntParserImpl
import com.example.pr11.kotlin.parsers.Parser
import com.example.pr11.kotlin.parsers.SexParserImpl

class AddCommand(
    override val name: String = CommandNames.ADD,
    override val description: String = "Команда добавления",
    override val example: String = "${CommandNames.ADD} surname name patronymic age(Int) sex(m/w)",
    override val neededNumberArgs: Int = 5,
    private val intParser: Parser<String, Int?> = IntParserImpl(),
    private val sexParser: Parser<String, Sex> = SexParserImpl()
) : Command {
    override fun execute(context: Context, args: List<String>) {
        if (args.size == neededNumberArgs) {
            val (_surname, _name, _patronymic, _age, _sex) = args
            val resAge = intParser.parse(_age)
            // интерфейс для валидации ENUM
            if (resAge == null) {
                println("Неверный возраст.")
            } else {
                val res = context.dataBase.add(context.studentFactory.create {
                    surname = _surname
                    name = _name
                    patronymic = _patronymic
                    age = resAge
                    sex = sexParser.parse(_sex)
                })
                if (res) {
                    println("Студент добавлен : ${context.dataBase.data.last()}.")
                } else {
                    println("Ошибка добавления.")
                }
            }
        } else {
            println("Ошибка параметров. Пример $example.")
        }
    }
}