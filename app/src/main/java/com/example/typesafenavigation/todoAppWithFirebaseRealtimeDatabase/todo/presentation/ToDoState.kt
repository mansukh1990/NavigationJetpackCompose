package com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.presentation

import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.domain.model.ToDoUI

data class ToDoState(
    val isLoading: Boolean = true,
    val toDoList: List<ToDoUI> = emptyList()

)