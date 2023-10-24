package com.technocraze.todomvp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Random

@Entity(tableName = "todo")
class TodoItem(
  @PrimaryKey(autoGenerate = true)
  var id: Long=Random().nextLong(),
  var title: String,
  var description: String)