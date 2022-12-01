package com.example.pr11.kotlin.student

import android.graphics.Bitmap
import com.example.pr11.kotlin.enums.Sex
import java.io.*

data class StudentImpl(
    override var surname: String = "",
    override var name: String = "",
    override var patronymic: String = "",
    override var age: Int = 0,
    override var sex: Sex = Sex.UNDEFINED,
    override var image: Bitmap? = null
) : Student {

    override fun toString(): String {
        return "$surname $name $patronymic $age $sex"
    }

//    companion object {
//        fun <T : Student> ByteArray.fromByteArray(): Collection<T> {
//            val byteArrayInputStream = ByteArrayInputStream(this)
//            val objectInput = ObjectInputStream(byteArrayInputStream)
//            val result = objectInput.readObject() as Collection<T>
//            objectInput.close()
//            byteArrayInputStream.close()
//            return result
//        }
//
//        fun <T : Student> Collection<T>.toByteArray(): ByteArray {
//            val byteArrayOutputStream = ByteArrayOutputStream()
//            val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
//            objectOutputStream.writeObject(this)
//            objectOutputStream.flush()
//            val result = byteArrayOutputStream.toByteArray()
//            byteArrayOutputStream.close()
//            objectOutputStream.close()
//            return result
//        }
//
//    }

}