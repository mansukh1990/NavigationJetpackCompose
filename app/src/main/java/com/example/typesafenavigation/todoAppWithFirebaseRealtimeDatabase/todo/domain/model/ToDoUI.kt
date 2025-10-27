package com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.domain.model

import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.core.utils.Priority

data class ToDoUI(
    val id: String? = null,
    val title: String? = null,
    val description: String = "",
    val priority: Priority = Priority.LOW,
    val dateAdded : String? = null
)