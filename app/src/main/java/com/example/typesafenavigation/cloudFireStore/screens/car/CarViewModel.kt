package com.example.typesafenavigation.cloudFireStore.screens.car

import Car
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CarViewModel : ViewModel() {

    private var _car = MutableStateFlow<Car?>(null)
    val car = _car.asStateFlow()

    init {
        getCarById()

    }

    fun getCarById() {
        val db = Firebase.firestore

        db.collection("cars")
            .document("FZFGxFRVZkWXbhgfxUaM")
            .get()
            .addOnSuccessListener { documentSnapshot ->
                _car.value = documentSnapshot.toObject(Car::class.java)
            }

    }
}