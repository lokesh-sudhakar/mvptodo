package com.technocraze.todomvp.addtodo

import android.content.Context
import com.technocraze.todomvp.model.TodoDatabase
import com.technocraze.todomvp.model.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddTodoPresenter(context: Context,
                       view: AddTodoContract.View): AddTodoContract.Presenter, CoroutineScope {

  val job: Job = Job()
  override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.IO



  private val todoDatabase = TodoDatabase.getInstance(context.applicationContext)
  private val todoDao = todoDatabase.todoDao()


  override fun addTodo(item: TodoItem) {
    launch {
      todoDao.insert(item)
    }
  }

  override fun onDetach() {
    job.cancel()
  }

}