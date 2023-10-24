package com.technocraze.todomvp.addtodo

import com.technocraze.todomvp.model.TodoItem

interface AddTodoContract {

  interface View{

  }

  interface Presenter{

    fun addTodo(item: TodoItem)

    fun onDetach()

  }

}