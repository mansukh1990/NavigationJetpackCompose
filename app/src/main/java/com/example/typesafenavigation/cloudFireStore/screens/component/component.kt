package com.example.typesafenavigation.cloudFireStore.screens.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun AddCarDialog(
    onDismiss: () -> Unit,
    onSubmit: (Int, String) -> Unit
) {
    var carId by remember { mutableStateOf("") }
    var carBrand by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Add New Car") },
        text = {
            Column {
                OutlinedTextField(
                    value = carId,
                    onValueChange = {
                        carId = it.filter { char -> char.isDigit() }
                    }, // accept only digits for id
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
            Button(onClick = {
                val idInt = carId.toIntOrNull()
                if (idInt != null && carBrand.isNotBlank()) {
                    onSubmit(idInt, carBrand)
                    onDismiss()
                }
            }) {
                Text("Submit")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}