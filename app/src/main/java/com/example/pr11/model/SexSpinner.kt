package com.example.pr11.model

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.pr11.R
import com.example.pr11.kotlin.enums.Sex

class SexSpinner {



    companion object {

        fun convertToSex(string: String, fragment: Fragment): Sex {
            return when (string) {
                fragment.getString(R.string.woman) -> Sex.WOMAN
                fragment.getString(R.string.male) -> Sex.MAN
                else -> Sex.UNDEFINED
            }
        }

    }

}