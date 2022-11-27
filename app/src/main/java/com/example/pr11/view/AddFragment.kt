package com.example.pr11.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pr11.R
import com.example.pr11.databinding.FragmentAddBinding

class AddFragment:Fragment(R.layout.fragment_add) {

    private lateinit var binding: FragmentAddBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddBinding.bind(view)

        binding.addButton.setOnClickListener {

        }
    }

}