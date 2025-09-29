package com.example.typesafenavigation.todoappwithfirebase.todo.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.typesafenavigation.R
import com.example.typesafenavigation.todoappwithfirebase.core.presentation.component.ToDoDialog
import com.example.typesafenavigation.todoappwithfirebase.core.utils.Priority
import com.example.typesafenavigation.todoappwithfirebase.todo.domain.model.ToDoUI
import com.example.typesafenavigation.todoappwithfirebase.todo.presentation.components.ToDoItems
import kotlinx.coroutines.launch

@Composable
fun ToDoScreen(
    state: ToDoState,
    events: (ToDoEvents) -> Unit
) {
    val scope = rememberCoroutineScope()

    var showToDoDialog by rememberSaveable { mutableStateOf(false) }

    var isEditMode by rememberSaveable { mutableStateOf(false) }

    var selectedTitle by rememberSaveable { mutableStateOf("") }
    var selectedDescription by rememberSaveable { mutableStateOf("") }
    var selectedPriority by rememberSaveable { mutableStateOf(Priority.LOW) }
    var selectedId by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.dark_blue)),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.toDoList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.toDoList, key = {
                    it.id!!
                }) { currentToDoItem ->
                    ToDoItems(toDoUI = currentToDoItem) {
                        selectedId = currentToDoItem.id!!
                        selectedTitle = currentToDoItem.title!!
                        selectedDescription = currentToDoItem.description
                        selectedPriority = currentToDoItem.priority
                        isEditMode = true
                        showToDoDialog = true


                    }
                }
            }
        } else {
            Text(
                text = "No ToDos, Please add some ToDo!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.light1_blue)
            )
        }
        FloatingActionButton(
            modifier = Modifier
                .size(100.dp)
                .padding(20.dp)
                .align(Alignment.BottomEnd),
            onClick = {
                showToDoDialog = true
            },
            containerColor = colorResource(R.color.medium_blue)
        ) {
            Icon(
                modifier = Modifier.size(50.dp),
                imageVector = Icons.Default.Add, contentDescription = "add", tint = Color.White
            )
        }
        if (showToDoDialog) {
            ToDoDialog(
                isEditMode = isEditMode,
                onDismiss = {
                    showToDoDialog = false
                },
                onAddToDo = { title, description, priority ->
                    scope.launch {
                        val toDoUi = ToDoUI(
                            title = title,
                            description = description,
                            priority = priority

                        )
                        events(ToDoEvents.SaveToDo(toDoUi))
                    }
                    showToDoDialog = false
                },
                onUpdateToDo = { title, description, priority ->
                    scope.launch {
                        val toDoUi = ToDoUI(
                            id = selectedId,
                            title = title,
                            description = description,
                            priority = priority,
                        )
                        events(ToDoEvents.UpdateToDo(toDoUi))
                    }
                    showToDoDialog = false
                },
                onDeleteToDo = {
                    scope.launch {
                        events(ToDoEvents.DeleteToDo(selectedId))
                        showToDoDialog = false
                    }
                },
                existingTitle = selectedTitle,
                existingDescription = selectedDescription,
                existingPriority = selectedPriority,
            )
        }

    }
}