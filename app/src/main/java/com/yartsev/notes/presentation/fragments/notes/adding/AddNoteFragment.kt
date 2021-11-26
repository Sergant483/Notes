package com.yartsev.notes.presentation.fragments.notes.adding

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.yartsev.notes.data.database.entity.NotesEntity
import com.yartsev.notes.databinding.AddNoteFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    private val viewModel: AddNoteViewModel by viewModels()
    private var _binding: AddNoteFragmentBinding? = null
    private val binding get() = _binding!!
    private var imageUri: Uri? = null
    private val getImage: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->
            Glide.with(requireContext()).load(imageUri).centerCrop().into(binding.image)
            this.imageUri = imageUri
            binding.delImage.isVisible = true
        }

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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Glide.with(requireContext()).load(data?.data).centerCrop().into(binding.image)
//            imageUri = data?.data
//            binding.delImage.isVisible = true
//        }
//    }

    private fun initClickListeners() {
        binding.saveButton.setOnClickListener {
            val note = NotesEntity(
                text = binding.textInput.editText?.text.toString(),
                imageUri = if (imageUri == null) "" else imageUri.toString()
            )
            if (note.text.isNotEmpty() || note.imageUri.isNotEmpty()) {
                    viewModel.saveNote(note).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                        findNavController().navigateUp()
                    }
            }
        }
        binding.addImageButton.setOnClickListener {
            getImage.launch(IMAGE_INTENT_TYPE)
//            val photoPickerIntent = Intent(Intent.ACTION_PICK)
//            photoPickerIntent.type = IMAGE_INTENT_TYPE
//            startActivityForResult(photoPickerIntent, REQUEST_IMAGE_CAPTURE)
        }
        binding.delImage.setOnClickListener {
            binding.image.setImageResource(0)
            imageUri = null
            binding.delImage.isVisible = false
        }
    }


    companion object {
        private const val IMAGE_INTENT_TYPE = "image/*"
        private const val REQUEST_IMAGE_CAPTURE = 2
    }

}