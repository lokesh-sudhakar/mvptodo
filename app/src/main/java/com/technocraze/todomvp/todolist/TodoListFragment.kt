package com.technocraze.todomvp.todolist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technocraze.todomvp.databinding.FragmentTodoListBinding
import com.technocraze.todomvp.model.TodoItem
import java.lang.IllegalArgumentException

interface TodoListFragmentClickListener {

  fun onFabClick()

}


class TodoListFragment : Fragment(), TodoContract.View {


  private lateinit var context: Context
  private lateinit var binding: FragmentTodoListBinding
  private lateinit var recyclerView: RecyclerView
  private var todoItems: ArrayList<TodoItem> = arrayListOf()
  private lateinit var activity: TodoListFragmentClickListener
  private lateinit var todoAdapter: TodoAdapter;

  private lateinit var presenter: TodoContract.Presenter

  private var param1: String? = null
  private var param2: String? = null

  override fun onAttach(context: Context) {
    Log.d(TAG, "onAttach: ")
    super.onAttach(context)
    this.context = context
    if (context is TodoListFragmentClickListener) {
      activity = context
    } else {
      throw IllegalArgumentException()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    Log.d(TAG, "onCreate: ")
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    Log.d(TAG, "onCreateView: ")
    binding = FragmentTodoListBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    Log.d(TAG, "onViewCreated: ")
    presenter = TodoListPresenter(context, this)
    initView()

  }

  private fun initView() {
    recyclerView = binding.rvTodo
    todoAdapter = TodoAdapter(todoItems) {
      presenter.delete(it)
    }
    recyclerView.apply {
      layoutManager = LinearLayoutManager(this.context)
      adapter = todoAdapter
    }

    binding.fabItem.setOnClickListener {
      activity.onFabClick()
    }
  }

  override fun onResume() {
    super.onResume()
    presenter.getAllTodos().observe(viewLifecycleOwner) {
      todoAdapter.updateTodo(it)
    }
  }

  override fun onDetach() {
    super.onDetach()
    presenter.onDetach()
  }


  companion object {

    const val TAG = "ListFragment"

    @JvmStatic
    fun newInstance(param1: String, param2: String) =
      TodoListFragment().apply {
        arguments = Bundle().apply {
          // putString(ARG_PARAM1, param1)
          // putString(ARG_PARAM2, param2)
        }
      }
  }

  override fun updateList(list: List<TodoItem>) {
    todoAdapter.apply {
      updateTodo(list)
      notifyDataSetChanged()
    }
  }

  override fun getCoroutineScope(): Lifecycle {
    return requireActivity().lifecycle
  }
}