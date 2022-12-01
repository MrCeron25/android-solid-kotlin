package com.example.pr11.kotlin.student

import android.graphics.Bitmap
import com.example.pr11.kotlin.enums.Sex

interface Student {
    var surname: String
    var name: String
    var patronymic: String
    var age: Int
    var sex: Sex
    var image: Bitmap?
}