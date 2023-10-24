package com.technocraze.todomvp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.technocraze.todomvp.db.TodoDao


@Database(entities = [TodoItem::class],version=1)
abstract class TodoDatabase: RoomDatabase() {

  abstract fun todoDao(): TodoDao

  companion object{
    fun getInstance(context: Context): TodoDatabase {
      return Room.databaseBuilder(context =context ,TodoDatabase::class.java,"todo_database").build()
    }
  }


}