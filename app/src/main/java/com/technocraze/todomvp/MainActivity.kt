package com.technocraze.todomvp

import android.os.Bundle
import android.util.Log
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.technocraze.todomvp.addtodo.AddTodoFragment
import com.technocraze.todomvp.todolist.TodoListFragment
import com.technocraze.todomvp.todolist.TodoListFragmentClickListener


class MainActivity : AppCompatActivity() , TodoListFragmentClickListener {

  
  val TAG = "MainActivity"
  private lateinit var fragmentTransaction: FragmentTransaction

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val fragment = TodoListFragment()
    val fragmentManager: FragmentManager = supportFragmentManager
    fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.apply {
      replace(R.id.fragment_container, fragment)
      addToBackStack("list")
      commit()
    }
    handleBack()
  }

  private fun handleBack() {
    onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        if (supportFragmentManager.backStackEntryCount==1){
          finish()
        } else {
          supportFragmentManager.popBackStack()
        }
      }
    })
  }

  fun showAddTodoDialog() {
    var dialogFragment = AddTodoFragment()
    supportFragmentManager.beginTransaction().apply {
      replace(R.id.fragment_container, dialogFragment)
        .addToBackStack("add")
      commit()
    }
  }

  override fun onFabClick() {
    showAddTodoDialog()
  }

  override fun onPause() {
    super.onPause()
    Log.d(TAG, "onPause: ")
  }

  override fun onStop() {
    super.onStop()
    Log.d(TAG, "onStop: ")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "onDestroy: ")
  }
  
  


}