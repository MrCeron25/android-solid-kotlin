package com.example.pr11.model.validators

class FullNameValidator : Validator<String> {

    private fun String.onlyLetters() = all { it.isLetter() }

    override fun isValid(data: String): Boolean = data.trim().onlyLetters()

    override fun isNotValid(data: String): Boolean = !isValid(data)

}