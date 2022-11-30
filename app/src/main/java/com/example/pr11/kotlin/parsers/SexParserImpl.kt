package com.example.pr11.kotlin.parsers

import com.example.pr11.R
import com.example.pr11.kotlin.enums.Sex
import com.example.pr11.view_model.BaseViewModel
import java.util.*

interface SexParserFromView<T, R> {
    fun getSexFromResourcesName(name: T): R
    val man: T
    val woman: T
    val undefined: T
}

class SexParserImpl : Parser<String, Sex>, SexParserFromView<String, Sex> {

    override fun parse(data: String): Sex {
        return when (data.trim().lowercase(Locale.getDefault())) {
            "m" -> Sex.MAN
            "w" -> Sex.WOMAN
            else -> Sex.UNDEFINED
        }
    }

    override val man = BaseViewModel.instance.activityViewModel.getString(R.string.male)

    override val woman = BaseViewModel.instance.activityViewModel.getString(R.string.woman)

    override fun getSexFromResourcesName(name: String): Sex {
        return when (name) {
            BaseViewModel.instance.activityViewModel.getString(R.string.woman) -> Sex.WOMAN
            BaseViewModel.instance.activityViewModel.getString(R.string.male) -> Sex.MAN
            else -> Sex.UNDEFINED
        }
    }

    override val undefined = BaseViewModel.instance.activityViewModel.getString(R.string.undefined)

}