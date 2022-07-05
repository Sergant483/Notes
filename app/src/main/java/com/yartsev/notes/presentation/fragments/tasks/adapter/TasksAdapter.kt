package com.yartsev.notes.presentation.fragments.notes.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yartsev.notes.data.database.entity.TasksEntity
import com.yartsev.notes.databinding.TasksItemBinding
import java.util.*
import kotlin.Comparator

typealias ItemClickListener = (position: Int) -> Unit

class TasksAdapter(private val ItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {
    private var taskItems = mutableListOf<TasksEntity>()

    fun setNotesList(tasks: List<TasksEntity>) {
        this.taskItems = tasks.toMutableList()
        notifyDataSetChanged()
    }

//    fun sort() {
//        Collections.sort(
//            this.taskItems,
//            Comparator { o1, o2 -> o1.StateDone.compareTo(o2.StateDone) })
//        setNotesList(taskItems)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        return TasksViewHolder(
            TasksItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(taskItems[position])
    }

    override fun getItemCount(): Int = taskItems.size

    inner class TasksViewHolder(private val binding: TasksItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(taskItem: TasksEntity) {
            //binding.taskText.text = taskItem.text
            doDoneOrUndoneTask(binding, taskItem)
            binding.noteCardView.setOnClickListener {
                ItemClickListener(bindingAdapterPosition)
                doDoneOrUndoneTask(binding, taskItem)
            }
        }
    }

    private fun doDoneOrUndoneTask(binding: TasksItemBinding, taskItem: TasksEntity) {
//        if (taskItem.StateDone)
//            binding.taskText.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
//        else {
//            binding.taskText.paintFlags = Paint.ANTI_ALIAS_FLAG
//        }
    }
}