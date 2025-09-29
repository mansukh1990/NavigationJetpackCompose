package com.example.typesafenavigation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SecondScreenComposable(name: String, age: String, onclick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray),
        contentAlignment = Alignment.Center
    ) {

        Button(
            onClick = onclick
        ) {
            Column {
                Text("age -> $age, name -> $name")
                Spacer(Modifier.height(15.dp))
                Text(text = "Go To First Screen")
            }

        }
    }
}