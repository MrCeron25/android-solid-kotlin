package com.example.pr11.kotlin.parsers

class ArgParserImpl : ArgParser {
    override fun parse(args: List<String>): Map<String, String> {
        return args
            .filter { it.isNotEmpty() }
            .map { it.split("=") }
//            .associate { it.first() to it.last() }
            .associate { it.let { (first, last) -> first to last } }
    }
}