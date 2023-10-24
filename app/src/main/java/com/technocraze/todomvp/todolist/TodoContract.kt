package com.technocraze.todomvp.todolist

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import com.technocraze.todomvp.model.TodoItem

interface TodoContract {

  interface View {
    fun updateList(list: List<TodoItem>)
    fun getCoroutineScope(): Lifecycle
  }

  interface Presenter {

    val todoLiveData : LiveData<List<TodoItem>>


    fun onDetach()

    fun add(item: TodoItem)

    fun delete(item: TodoItem)

    fun getAllTodos(): LiveData<List<TodoItem>>

  }

}