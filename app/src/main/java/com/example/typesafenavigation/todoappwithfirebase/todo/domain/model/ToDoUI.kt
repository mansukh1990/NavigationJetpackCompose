package com.example.typesafenavigation.todoappwithfirebase.todo.domain.model

import com.example.typesafenavigation.todoappwithfirebase.core.utils.Priority

data class ToDoUI(
    val id: String? = null,
    val title: String? = null,
    val description: String = "",
    val priority: Priority = Priority.LOW,
    val dateAdded : String? = null
)