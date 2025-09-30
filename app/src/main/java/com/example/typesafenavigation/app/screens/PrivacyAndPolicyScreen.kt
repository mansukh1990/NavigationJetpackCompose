package com.example.typesafenavigation.app.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.typesafenavigation.R
import com.example.typesafenavigation.app.components.HeadingTextComponent

@Composable
fun PrivacyAndPolicyScreen(onBack: () -> Unit) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color.White
    ) {
        HeadingTextComponent(value = stringResource(id = R.string.str_privacy_policy))
    }

}