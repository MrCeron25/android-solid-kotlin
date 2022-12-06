package com.example.pr11.model.validators

interface Validator<T> {

    fun isValid(data: T): Boolean

    fun isNotValid(data: T): Boolean

}