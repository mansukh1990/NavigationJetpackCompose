package com.example.typesafenavigation.todoappwithfirebase.todo.domain.repository

import com.example.typesafenavigation.todoappwithfirebase.todo.domain.model.ToDoUI
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    suspend fun saveToDo(toDoUI: ToDoUI)
    suspend fun getToDo(): Flow<List<ToDoUI>>
    suspend fun updateToDO(toDoUI: ToDoUI)
    suspend fun deleteToDo(id: String)
}