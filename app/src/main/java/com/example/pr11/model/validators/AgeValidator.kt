package com.example.pr11.model.validators

class AgeValidator : Validator<Int> {

    override fun isValid(data: Int): Boolean = data >= 10

    override fun isNotValid(data: Int): Boolean = !isValid(data)

}