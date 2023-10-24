package com.technocraze.todomvp.addtodo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.technocraze.todomvp.databinding.FragmentAddTodoBinding
import com.technocraze.todomvp.model.TodoItem
import com.technocraze.todomvp.todolist.TodoContract
import com.technocraze.todomvp.todolist.TodoListPresenter


class AddTodoFragment : Fragment(), AddTodoContract.View {

  private lateinit var binding: FragmentAddTodoBinding
  private lateinit var context: Context
  private lateinit var presenter: AddTodoContract.Presenter

  override fun onAttach(context: Context) {
    super.onAttach(context)
    this.context = context
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = FragmentAddTodoBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter = AddTodoPresenter(context, this)

    binding.saveButton.setOnClickListener {
      val title: String = binding.editTitle.text.toString()
      val description: String = binding.editTitle.text.toString()
      presenter.addTodo(TodoItem(title = title, description = description))
      parentFragmentManager.popBackStack()
    }
  }



}
