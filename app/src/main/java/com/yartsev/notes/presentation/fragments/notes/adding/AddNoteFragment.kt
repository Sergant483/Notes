package com.yartsev.notes.presentation.fragments.notes.adding

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.yartsev.notes.data.database.entity.NotesEntity
import com.yartsev.notes.databinding.AddNoteFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers


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
    private val noteId by lazy { arguments?.getInt(BUNDLE_KEY, 0) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddNoteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initClickListeners()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initData() {
        if (noteId != null) {
            viewModel.getNoteById(noteId!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<NotesEntity>() {
                    override fun onSuccess(note: NotesEntity) {
                        binding.textInput.editText?.setText(note.text)
                        imageUri = note.imageUri.toUri()
                        Glide.with(requireContext()).load(imageUri).centerCrop().into(binding.image)
                        viewModel.deleteNoteById(noteId!!).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {}
                    }

                    override fun onError(e: Throwable) {}
                })
        }
    }

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
        binding.addImageButton.setOnClickListener { getImage.launch(IMAGE_INTENT_TYPE) }
        binding.delImage.setOnClickListener {
            binding.image.setImageResource(0)
            imageUri = null
            binding.delImage.isVisible = false
        }
    }


    companion object {
        const val BUNDLE_KEY = "KEY"
        private const val IMAGE_INTENT_TYPE = "image/*"
    }

}