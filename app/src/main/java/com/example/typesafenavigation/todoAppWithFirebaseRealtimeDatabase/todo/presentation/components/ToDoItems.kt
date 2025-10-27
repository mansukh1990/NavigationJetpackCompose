package com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.typesafenavigation.R
import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.core.utils.Priority
import com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.todo.domain.model.ToDoUI

@Composable
fun ToDoItems(
    modifier: Modifier = Modifier,
    toDoUI: ToDoUI,
    onItemClicked: () -> Unit
) {
    val containerColor = when (toDoUI.priority) {
        Priority.LOW -> colorResource(R.color.green)
        Priority.MEDIUM -> colorResource(R.color.yellow)
        Priority.HIGH -> colorResource(R.color.red)
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                onItemClicked()
            }
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(15.dp),
                spotColor = containerColor
            ),
        colors = CardDefaults.cardColors().copy(
            containerColor = containerColor
        ),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, Color.White)
    ) {
        Column(modifier = modifier.padding(10.dp)) {
            Text(
                text = toDoUI.title!!,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (toDoUI.description != "") {
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = toDoUI.description,
                    fontSize = 18.sp,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = modifier.height(16.dp))
            Text(
                modifier = modifier.fillMaxWidth(),
                text = toDoUI.dateAdded!!,
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.End
            )


        }
    }

}

