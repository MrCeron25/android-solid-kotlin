package com.example.pr11.view_model

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.ViewModel

class BaseViewModel : ViewModel() {

    val dataBaseViewModel = DataBaseViewModel.instance
    val activityViewModel = ActivityViewModel.instance

    class ActivityViewModel : ViewModel() {
        @SuppressLint("StaticFieldLeak")
        private var activity: Activity? = null

        fun getString(res: Int): String? = activity?.getString(res)

        fun setActivity(_activity: Activity) {
            activity = _activity
        }

        fun finishActivity() {
            activity?.finish()
        }

        companion object {
            val instance by lazy { ActivityViewModel() }
        }
    }

    companion object {
        val instance by lazy { BaseViewModel() }
    }
}