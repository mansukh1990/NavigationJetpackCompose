package com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.data.model

import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.core.utils.Priority

data class ToDoDTO(
    val id: String? = null,
    val title: String? = null,
    val description: String = "",
    val priority: Priority = Priority.LOW,
    val dateAdded: Long? = null
)
