package com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.data.mapper

import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.data.model.ToDoDTO
import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.domain.model.ToDoUI
import java.util.UUID

fun ToDoUI.toToDoDTO(): ToDoDTO {
    return ToDoDTO(
        id = id ?: UUID.randomUUID().toString(),
        title = title,
        description = description,
        priority = priority,
        dateAdded = System.currentTimeMillis()

    )
}