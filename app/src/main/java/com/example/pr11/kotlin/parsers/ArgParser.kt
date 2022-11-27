package com.example.pr11.kotlin.parsers

interface ArgParser {
    fun parse(args: List<String>): Map<String, Any>
}