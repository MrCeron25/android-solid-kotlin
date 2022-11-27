package com.example.pr11.kotlin.parsers

import com.example.pr11.kotlin.enums.Sex
import java.util.*


class SexParserImpl : Parser<String, Sex> {

    override fun parse(data: String): Sex {
        return when (data.trim().lowercase(Locale.getDefault())) {
            "m" -> Sex.MAN
            "w" -> Sex.WOMAN
            else -> Sex.UNDEFINED
        }
    }

}