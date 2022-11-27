package com.example.pr11.kotlin.commands

import com.example.pr11.kotlin.context.Context
import com.example.pr11.kotlin.enums.CommandNames
import com.example.pr11.kotlin.enums.Sex
import com.example.pr11.kotlin.parsers.IntParserImpl
import com.example.pr11.kotlin.parsers.Parser
import com.example.pr11.kotlin.parsers.SexParserImpl

class ChangeCommand(
    override val name: String = CommandNames.CHANGE,
    override val description: String = "Команда изменения",
    override val example: String = "${CommandNames.CHANGE} changeIndex surname name patronymic age(Int) sex(m/w)",
    override val neededNumberArgs: Int = 6,
    private val intParser: Parser<String, Int?> = IntParserImpl(),
    private val sexParser: Parser<String, Sex> = SexParserImpl()
) : Command {
    // /change 1 qwe asd zxc 456 m
    override fun execute(context: Context, args: List<String>) {
        if (args.size == neededNumberArgs) {
            val (_index, _surname, _name, _patronymic, _age, _sex) = args
            val resIndex = intParser.parse(_index)
            if (resIndex != null) {
                val resAge = intParser.parse(_age)
                if (resAge != null) {
                    val res = context.dataBase.change(resIndex, context.studentFactory.create {
                        surname = _surname
                        name = _name
                        patronymic = _patronymic
                        age = resAge
                        sex = sexParser.parse(_sex)
                    })
                    if (res) {
                        println("Студент №$resIndex изменён : ${context.dataBase.data[resIndex - 1]}")
                    } else {
                        println("Ошибка изменения.")
                    }
                } else {
                    println("Неверный возраст.")
                }
            } else {
                println("Неверный номера записи.")
            }
        } else {
            println("Ошибка параметров. Пример $example.")
        }
    }
}

// https://stackoverflow.com/questions/57035733/destructuring-declaration-initializer-of-type-liststring-must-have-a-componen
private operator fun <E> List<E>.component6(): E = get(5)
