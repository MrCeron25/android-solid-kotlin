package com.example.pr11.kotlin.parsers

import android.view.View
import com.example.pr11.R
import com.example.pr11.kotlin.enums.Sex
import java.util.*

interface SexParserFromView<T, R> {
    fun getSex(view: View, sex: T): R
    fun getMale(view: View): T
    fun getWoman(view: View): T
    fun getUndefined(view: View): T
}

class SexParserImpl : Parser<String, Sex>, SexParserFromView<String, Sex> {

    override fun parse(data: String): Sex {
        return when (data.trim().lowercase(Locale.getDefault())) {
            "m" -> Sex.MAN
            "w" -> Sex.WOMAN
            else -> Sex.UNDEFINED
        }
    }

    override fun getMale(view: View): String {
        return view.context.getString(R.string.male)
    }

    override fun getWoman(view: View): String {
        return view.context.getString(R.string.woman)
    }

    override fun getSex(view: View, sex: String): Sex {
        return when (sex) {
            view.context.getString(R.string.woman) -> Sex.WOMAN
            view.context.getString(R.string.male) -> Sex.MAN
            else -> Sex.UNDEFINED
        }
    }

    override fun getUndefined(view: View): String {
        return view.context.getString(R.string.undefined)
    }

}