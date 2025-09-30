package com.example.typesafenavigation.textwithshadow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.typesafenavigation.R

@Composable
fun TextWithShadow(value: String, shadowColor: Color) {

    val shadowOffset = Offset(x = 4f, y = 6f)

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.LightGray)
            .padding(18.dp),
        text = "${stringResource(id = R.string.app_name)} $value",
        style = TextStyle(
            fontSize = 24.sp,
            fontStyle = FontStyle.Normal,
            shadow = androidx.compose.ui.graphics.Shadow(
                color = shadowColor,
                offset = shadowOffset,
                blurRadius = 2f
            )
        )
    )
}

@Composable
fun TextMainScreen(modifier: Modifier = Modifier) {

    Column {
        TextWithShadow(value = "Android", shadowColor = Color.Red)
        TextWithShadow(value = "Kotlin", shadowColor = Color.Cyan)
    }
}