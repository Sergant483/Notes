package com.yartsev.notes.presentation.fragments.notes.adding

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yartsev.notes.databinding.AddNoteFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import kotlin.math.log


@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    private val viewModel: AddNoteViewModel by viewModels()
    private var _binding: AddNoteFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddNoteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    override fun onPause() {
        if (binding.textInput.editText?.text?.isNotEmpty() == true) showToast()
        super.onPause()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Glide.with(requireContext()).load(data?.data).centerCrop().into(binding.image)
            binding.delImage.isVisible = true
        }
    }

    private fun initClickListeners() {
        binding.saveButton.setOnClickListener {
            if (binding.textInput.editText?.text?.isNotEmpty() == true) showToast()
        }

        binding.addImageButton.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = IMAGE_INTENT_TYPE
            startActivityForResult(photoPickerIntent, REQUEST_IMAGE_CAPTURE)
        }
        binding.delImage.setOnClickListener {
            binding.image.setImageResource(0)
            binding.delImage.isVisible = false
        }
    }

    private fun showToast() =
        Toast.makeText(requireContext(), "SAVE?", Toast.LENGTH_LONG).show()

    companion object {
        private const val IMAGE_INTENT_TYPE = "image/*"
        private const val REQUEST_IMAGE_CAPTURE = 2

    }

}