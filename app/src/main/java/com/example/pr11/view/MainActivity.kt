package com.example.pr11.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pr11.R
import com.example.pr11.view_model.BaseViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BaseViewModel.instance.activityViewModel.setActivity(this)
    }

}