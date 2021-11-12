package com.yartsev.notes.presentation.fragments.tasks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yartsev.notes.R
import com.yartsev.notes.databinding.NotesFragmentBinding
import com.yartsev.notes.databinding.TasksFragmentBinding
import com.yartsev.notes.presentation.fragments.notes.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment() {
    private val viewModel: TasksViewModel by viewModels()
    private var _binding: TasksFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TasksFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}