package com.example.typesafenavigation.cloudFireStore.screens.cars

import Car
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CarListViewModel : ViewModel() {

    private var _carsList = MutableStateFlow<List<Car>>(emptyList())
    val carsList = _carsList.asStateFlow()

    private val db = Firebase.firestore


    init {
        getCarList()
    }

    private fun getCarList() {

        db.collection("cars")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                if (value != null) {
                    val cars = value.documents.mapNotNull { doc ->
                        doc.toObject(Car::class.java)?.copy(docId = doc.id)

                    }
                    _carsList.value = cars
                }
            }
    }

    fun createOrUpdateCar(documentId: String?, car: Car) {
        val carMap = hashMapOf(
            "id" to car.id,
            "brand" to car.brand
        )
        if (documentId == null) {
            db.collection("cars")
                .add(carMap)
        } else {
            db.collection("cars")
                .document(documentId)
                .set(carMap)
        }
    }

    fun addCar(documentId: String?, car: Car) {

        val newCar = hashMapOf(
            "id" to car.id,
            "brand" to car.brand
        )
        if (documentId == null) {
            db.collection("cars")
                .add(newCar)
                .addOnSuccessListener {
                    Log.d("document", "Add successfully")
                }
                .addOnFailureListener {
                    Log.d("document", "Add failed")
                }
        }


    }

    fun updateCar(documentId: String?, car: Car) {
        val updatedCar = hashMapOf(
            "id" to car.id,
            "brand" to car.brand
        )
        car.docId?.let { docId ->
            db.collection("cars")
                .document(documentId!!)
                .set(updatedCar)
                .addOnSuccessListener {
                    Log.d("document", "Update Successfully")
                }
                .addOnFailureListener {
                    Log.d("document", "Update failed")
                }


        }

    }

    fun deleteCar(docId: String) {
        db.collection("cars")
            .document(docId)
            .delete()
            .addOnSuccessListener {
                Log.d("Firestore", "Car deleted successfully")
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error deleting car", e)
            }
    }

}