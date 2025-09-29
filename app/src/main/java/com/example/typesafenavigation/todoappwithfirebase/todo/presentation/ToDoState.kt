package com.example.typesafenavigation.todoappwithfirebase.todo.presentation

import com.example.typesafenavigation.todoappwithfirebase.todo.domain.model.ToDoUI

data class ToDoState(
    val isLoading: Boolean = true,
    val toDoList: List<ToDoUI> = emptyList()

)