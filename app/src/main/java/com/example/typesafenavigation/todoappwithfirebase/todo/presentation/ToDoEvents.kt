package com.example.typesafenavigation.todoappwithfirebase.todo.presentation

import com.example.typesafenavigation.todoappwithfirebase.todo.domain.model.ToDoUI

sealed class ToDoEvents {

    data class SaveToDo(val toDoUI: ToDoUI) : ToDoEvents()

    data class UpdateToDo(val toDoUI: ToDoUI) : ToDoEvents()
    data class DeleteToDo(val id: String) : ToDoEvents()
}