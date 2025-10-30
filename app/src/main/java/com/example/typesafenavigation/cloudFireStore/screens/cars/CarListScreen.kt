package com.example.typesafenavigation.cloudFireStore.screens.cars

import Car
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CarListScreen(
    viewModel: CarListViewModel = viewModel()
) {

    val cars by viewModel.carsList.collectAsStateWithLifecycle()
    var showAddDialog by remember { mutableStateOf(false) }
    var showUpdateDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var currentCar by remember { mutableStateOf<Car?>(null) }

    Scaffold { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(20.dp),
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(cars) { car ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "${car.id} - ${car.brand}",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = {
                            currentCar = car
                            showUpdateDialog = true

                        }) {
                            Icon(
                                Icons.Default.EditNote,
                                contentDescription = "Update",
                                tint = Color.Blue
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        IconButton(
                            onClick = {
                                currentCar = car
                                showDeleteDialog = true
                            }) {
                            Icon(
                                imageVector = Icons.Default.DeleteForever,
                                contentDescription = null,
                                tint = Color.Red,
                            )
                        }
                    }
                }

            }
            item {
                Button(onClick = {
                    currentCar = null
                    showAddDialog = true
                }) {
                    Text(
                        text = "Add New Car"
                    )
                }
            }

        }

    }
    //add dialog
    CarDialog(
        showDialog = showAddDialog,
        isUpdate = false,
        initialCar = null,
        onDismiss = {
            showAddDialog = false
        },
        onSubmit = { id, brand ->
            viewModel.addCar(
                currentCar?.docId,
                Car(id, brand)
            )
        }

    )

    // Update dialog
    CarDialog(
        showDialog = showUpdateDialog,
        isUpdate = true,
        initialCar = currentCar,
        onDismiss = {
            showUpdateDialog = false
            currentCar = null
        },
        onSubmit = { id, brand ->
            viewModel.updateCar(
                currentCar?.docId!!,
                Car(id, brand)
            )

        }
    )
    DeleteCarDialog(
        showDialog = showDeleteDialog,
        car = currentCar,
        onDismiss = {
            showDeleteDialog = false
            currentCar = null
        },
        onDelete = {
            currentCar?.docId?.let { docId ->
                viewModel.deleteCar(docId)
                showDeleteDialog = false
                currentCar = null

            }
        }
    )

}

@Composable
fun DeleteCarDialog(
    showDialog: Boolean,
    car: Car?,
    onDismiss: () -> Unit,
    onDelete: () -> Unit
) {
    if (showDialog && car != null) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Delete Car") },
            text = { Text("Are you sure you want to delete ${car.brand}?") },
            confirmButton = {
                Button(onClick = onDelete) { Text("Delete") }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) { Text("Cancel") }
            }
        )
    }
}


@Composable
fun CarDialog(
    showDialog: Boolean,
    isUpdate: Boolean,
    initialCar: Car?,
    onDismiss: () -> Unit,
    onSubmit: (Int, String) -> Unit
) {
    var carId by remember { mutableStateOf("") }
    var carBrand by remember { mutableStateOf("") }

    LaunchedEffect(showDialog, isUpdate, initialCar) {
        if (showDialog) {
            if (isUpdate && initialCar != null) {
                carId = initialCar.id.toString()
                carBrand = initialCar.brand
            } else {
                carId = ""
                carBrand = ""
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(if (isUpdate) "Update Car" else "Add New Car") },
            text = {
                Column {
                    OutlinedTextField(
                        value = carId,
                        onValueChange = { carId = it.filter { char -> char.isDigit() } },
                        label = { Text("Car ID") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    OutlinedTextField(
                        value = carBrand,
                        onValueChange = { carBrand = it },
                        label = { Text("Car Brand") },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val idInt = carId.toIntOrNull()
                        if (idInt != null && carBrand.isNotBlank()) {
                            onSubmit(idInt, carBrand)
                            onDismiss()
                        }
                    }) {
                    Text(if (isUpdate) "Update" else "Add")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) { Text("Cancel") }
            }
        )
    }
}


