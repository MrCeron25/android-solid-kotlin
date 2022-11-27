package com.example.pr11.kotlin.parsers

interface Parser<T, R> {
    fun parse(data: T): R
}