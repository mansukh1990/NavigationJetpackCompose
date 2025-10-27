package com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.data.repository

import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.data.mapper.toToDoDTO
import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.data.model.ToDoDTO
import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.domain.mapper.toToDoUI
import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.domain.model.ToDoUI
import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.domain.repository.ToDoRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ToDoRepositoryImpl : ToDoRepository {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val toDoRef = firebaseDatabase.getReference("ToDoItems")

    override suspend fun saveToDo(toDoUI: ToDoUI) {
        val toDoDTO = toDoUI.toToDoDTO()

        try {
            toDoRef.child(toDoDTO.id!!).setValue(toDoDTO).await()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getToDo(): Flow<List<ToDoUI>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val toDoUItems: List<ToDoUI> = snapshot.children.mapNotNull {
                    it.getValue(ToDoDTO::class.java)
                }.map {
                    it.toToDoUI()
                }
                trySend(toDoUItems)
            }

            override fun onCancelled(error: DatabaseError) {
                close()
            }

        }
        toDoRef.addValueEventListener(listener)
        awaitClose {
            toDoRef.removeEventListener(listener)
        }
    }

    override suspend fun updateToDO(toDoUI: ToDoUI) {
        val toDoDTO = toDoUI.toToDoDTO()
        try {
            toDoRef.child(toDoDTO.id!!).setValue(toDoDTO).await()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun deleteToDo(id: String) {
        try {
            toDoRef.child(id).removeValue().await()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}