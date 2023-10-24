package com.technocraze.todomvp.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technocraze.todomvp.databinding.TodoItemBinding
import com.technocraze.todomvp.model.TodoItem


class TodoAdapter(private val todoList: ArrayList<TodoItem>,val onDeleteClick:(TodoItem)->Unit) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
    var binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return TodoViewHolder(binding)
  }

  override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
    val item = todoList[position]
    holder.bind(item,onDeleteClick)
  }

  fun updateTodo(list: List<TodoItem>){
    this.todoList.clear()
    this.todoList.addAll(list)
    this.notifyDataSetChanged()
  }

  override fun getItemId(position: Int): Long {
    return todoList[position].id
  }

  override fun getItemCount(): Int {
    return todoList.size
  }

  class TodoViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item :TodoItem, onDeleteClick:(TodoItem)->Unit){
      binding.todoItem = item
      binding.executePendingBindings()
      binding.deleteButton.setOnClickListener {
        onDeleteClick(item)
      }
    }
  }


}