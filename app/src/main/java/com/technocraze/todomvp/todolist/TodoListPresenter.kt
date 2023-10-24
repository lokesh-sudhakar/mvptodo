package com.technocraze.todomvp.todolist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.coroutineScope
import com.technocraze.todomvp.model.TodoDatabase
import com.technocraze.todomvp.model.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class TodoListPresenter(
  context: Context,
  val view: TodoContract.View
) : TodoContract.Presenter, CoroutineScope {

  private val job = Job()
  override val coroutineContext: CoroutineContext = job + Dispatchers.IO

  private val todoDatabase = TodoDatabase.getInstance(context.applicationContext)
  private val todoDao = todoDatabase.todoDao()

  override val todoLiveData: LiveData<List<TodoItem>> = todoDao.getAllMovies()


  override fun add(item: TodoItem) {
    launch(Dispatchers.IO) {
      todoDao.insert(item)
    }
  }

  override fun delete(item: TodoItem) {
    launch(Dispatchers.IO) {
      todoDao.delete(item)
    }
  }

  override fun getAllTodos(): LiveData<List<TodoItem>> {
    return todoLiveData
  }

  override fun onDetach() {
    job.cancel()
  }


}