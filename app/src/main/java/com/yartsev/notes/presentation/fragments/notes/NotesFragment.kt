package com.yartsev.notes.presentation.fragments.notes

import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yartsev.notes.R
import com.yartsev.notes.data.database.entity.NotesEntity
import com.yartsev.notes.databinding.NotesFragmentBinding
import com.yartsev.notes.presentation.fragments.notes.adapter.NotesAdapter
import com.yartsev.notes.presentation.fragments.notes.util.SwipeController
import dagger.hilt.android.AndroidEntryPoint
import io.mobilation.bike.tracker.mobile.presentation.fragment.history.util.SwipeControllerActions
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


@AndroidEntryPoint
class NotesFragment : Fragment() {
    private val viewModel: NotesViewModel by viewModels()
    private var _binding: NotesFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = NotesAdapter(::onItemClick)

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


        binding.notesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.notesRecyclerView.adapter = adapter
        viewModel.getAllNotes().observeOn(AndroidSchedulers.mainThread())
            .subscribe { notes ->
                adapter.setNotesList(notes)
                initSwipeListener(notes)
            }
//        viewModel.notes.observe(viewLifecycleOwner, {
//            adapter.setNotesList(it)
//            Log.e("TAGG", "OBSERVE")
//
//        })
//        viewModel.getAllNotes()
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

    private fun onItemClick(itemIndex: Int) {}

    private fun initSwipeListener(notes: List<NotesEntity>) {
        val swipeController = SwipeController(object : SwipeControllerActions() {
            override fun onRightClicked(position: Int) {
                viewModel.deleteNote(notes[position].Id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                    viewModel.notes.value?.toMutableList()?.removeAt(position)
                    _binding?.notesRecyclerView?.adapter?.notifyItemRemoved(position)
                    super.onRightClicked(position)
                }
            }
        }, requireContext())
        ItemTouchHelper(swipeController).attachToRecyclerView(_binding?.notesRecyclerView)
        _binding?.notesRecyclerView?.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun onDraw(
                c: Canvas,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                swipeController.onDraw(c)
            }
        })
    }

}