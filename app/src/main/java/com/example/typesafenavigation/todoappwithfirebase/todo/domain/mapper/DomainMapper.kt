package com.example.typesafenavigation.todoappwithfirebase.todo.domain.mapper

import com.example.typesafenavigation.todoappwithfirebase.core.utils.formatToReadableToDate
import com.example.typesafenavigation.todoappwithfirebase.todo.data.model.ToDoDTO
import com.example.typesafenavigation.todoappwithfirebase.todo.domain.model.ToDoUI

fun ToDoDTO.toToDoUI(): ToDoUI {
    return ToDoUI(
        id = id!!,
        title = title!!,
        description = description,
        priority = priority,
        dateAdded = formatToReadableToDate(dateAdded!!)

    )
}