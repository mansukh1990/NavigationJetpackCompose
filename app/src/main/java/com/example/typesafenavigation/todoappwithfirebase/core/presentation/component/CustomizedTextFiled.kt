package com.example.typesafenavigation.todoappwithfirebase.core.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.typesafenavigation.R

@Composable
fun CustomizedTextFiled(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean,
    supportingText: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = { onValueChange(it) },
        enabled = enabled,
        label = {
            Text(text = label)
        },
        colors = OutlinedTextFieldDefaults.colors().copy(
            focusedTextColor = colorResource(R.color.light1_blue),
            unfocusedTextColor = colorResource(R.color.light1_blue),
            focusedContainerColor = colorResource(R.color.dark_blue),
            unfocusedContainerColor = colorResource(R.color.dark_blue),
            unfocusedLeadingIconColor = colorResource(R.color.light1_blue),
            focusedLeadingIconColor = colorResource(R.color.light1_blue),
            focusedIndicatorColor = colorResource(R.color.light1_blue),
            unfocusedIndicatorColor = colorResource(R.color.light1_blue),
            focusedLabelColor = colorResource(R.color.light1_blue),
            unfocusedLabelColor = colorResource(R.color.light1_blue),
            cursorColor = colorResource(R.color.light1_blue),
            disabledTextColor = Color.LightGray,
            disabledLabelColor = Color.LightGray,
            disabledIndicatorColor = Color.LightGray
        ),
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        supportingText = {
            Text(
                text = supportingText,
                color = Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

    )

}