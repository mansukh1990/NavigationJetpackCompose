package com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.domain.mapper

import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.core.utils.formatToReadableToDate
import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.data.model.ToDoDTO
import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.domain.model.ToDoUI

fun ToDoDTO.toToDoUI(): ToDoUI {
    return ToDoUI(
        id = id!!,
        title = title!!,
        description = description,
        priority = priority,
        dateAdded = formatToReadableToDate(dateAdded!!)

    )
}