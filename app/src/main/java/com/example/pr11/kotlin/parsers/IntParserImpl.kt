package com.example.pr11.kotlin.parsers

class IntParserImpl : Parser<String, Int?> {

    override fun parse(data: String): Int? {
        return data.toIntOrNull()
    }

}
