package com.yartsev.notes.presentation.fragments.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yartsev.notes.R
import com.yartsev.notes.databinding.NotesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment() : Fragment() {
    private val viewModel: NotesViewModel by viewModels()
    private var _binding: NotesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NotesFragmentBinding.inflate(inflater, container, false)
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

    private fun initClickListeners() {
        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_addNoteFragment)
        }
    }

}