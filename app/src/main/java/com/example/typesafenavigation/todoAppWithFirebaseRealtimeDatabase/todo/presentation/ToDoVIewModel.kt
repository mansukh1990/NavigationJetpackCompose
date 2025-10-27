package com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.data.repository.ToDoRepositoryImpl
import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.domain.model.ToDoUI
import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ToDoVIewModel : ViewModel() {

    val toDoRepository: ToDoRepository = ToDoRepositoryImpl()

    private val _state = MutableStateFlow(ToDoState())
    val state = _state.asStateFlow()

    init {
        getToDo()
    }

    fun onEvent(events: ToDoEvents) {
        when (events) {
            is ToDoEvents.SaveToDo -> {
                saveToDo(events.toDoUI)
            }

            is ToDoEvents.DeleteToDo -> {
                deleteToDO(events.id)
            }
            is ToDoEvents.UpdateToDo ->{
                updateToDo(events.toDoUI)
            }
        }
    }

    private fun saveToDo(toDoUI: ToDoUI) {
        viewModelScope.launch {
            toDoRepository.saveToDo(toDoUI)
        }

    }

    private fun getToDo() {
        viewModelScope.launch {
            toDoRepository.getToDo().collect { toDoList ->
                Log.d("ToDoList", toDoList.toString())
                _state.value = state.value.copy(
                    toDoList = toDoList,
                    isLoading = false
                )
            }
            _state.value = state.value.copy(
                isLoading = false
            )
        }
    }

    private fun updateToDo(toDoUI: ToDoUI) {
        viewModelScope.launch {
            toDoRepository.updateToDO(toDoUI)
        }
    }

    private fun deleteToDO(id: String) {
        viewModelScope.launch {
            toDoRepository.deleteToDo(id)
        }
    }
}