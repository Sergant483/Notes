package com.yartsev.notes.presentation.fragments.notes.adding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yartsev.notes.databinding.AddNoteFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

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

    private fun initClickListeners() {
        binding.saveButton.setOnClickListener {
            if (binding.textInput.editText?.text?.isNotEmpty() == true) showToast()
        }
    }

    private fun showToast() =
        Toast.makeText(requireContext(), "SAVE?", Toast.LENGTH_LONG).show()


}