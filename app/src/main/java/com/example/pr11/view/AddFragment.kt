package com.example.pr11.view

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pr11.R
import com.example.pr11.databinding.FragmentAddBinding
import com.example.pr11.kotlin.parsers.SexParserImpl
import com.example.pr11.model.SexSpinner
import com.example.pr11.view_model.BaseViewModel
import kotlinx.coroutines.launch


class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var binding: FragmentAddBinding
    private val viewModel: BaseViewModel by activityViewModels()

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        @Suppress("DEPRECATION")
        super.onActivityResult(requestCode, resultCode, data)

        if ((resultCode == RESULT_OK) && (requestCode == CAMERA_REQ_CODE)) {
            @Suppress("DEPRECATION") val img = data?.extras?.get("data") as Bitmap
            binding.photo.setImageBitmap(img)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddBinding.bind(view)
        SexSpinner().addSpinnerSexItems(view, binding.sexSpinner)

        binding.addButton.setOnClickListener {
            lifecycleScope.launch {
                val surname = binding.editTextSurname.text.toString()
                val name = binding.editTextName.text.toString()
                val patronymic = binding.editTextPatronymic.text.toString()
                val age = binding.editTextAge.text.toString().toIntOrNull() ?: 0
                val sex =
                    SexParserImpl().getSexFromResourcesName(binding.sexSpinner.selectedItem.toString())
                val photo = binding.photo.drawable.toBitmap()
                viewModel.dataBaseViewModel.add(surname, name, patronymic, age, sex, photo)
                findNavController().popBackStack()
            }
        }

        binding.addButtonPhoto.setOnClickListener {
            val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            @Suppress("DEPRECATION")
            startActivityForResult(intentCamera, CAMERA_REQ_CODE)
        }
    }

    companion object {
        const val CAMERA_REQ_CODE = 100
    }

}