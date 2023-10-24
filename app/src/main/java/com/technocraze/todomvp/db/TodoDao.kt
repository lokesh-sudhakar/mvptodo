package com.technocraze.todomvp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.technocraze.todomvp.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao{

  @Insert
  suspend fun insert(item: TodoItem)

  @Delete
  suspend fun delete(id: TodoItem)

  @Query("SELECT * FROM todo")
  fun getAllMovies(): LiveData<List<TodoItem>>


}