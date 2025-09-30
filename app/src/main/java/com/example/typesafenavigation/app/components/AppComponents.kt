package com.example.typesafenavigation.app.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.typesafenavigation.R
import com.example.typesafenavigation.ui.theme.bgColor
import com.example.typesafenavigation.ui.theme.componentShape
import com.example.typesafenavigation.ui.theme.grayColor
import com.example.typesafenavigation.ui.theme.primaryColor
import com.example.typesafenavigation.ui.theme.secondaryColor
import com.example.typesafenavigation.ui.theme.textColor

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(R.color.colorText),
        textAlign = TextAlign.Center
    )

}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(R.color.colorText),
        textAlign = TextAlign.Center
    )

}

@Composable
fun TextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onTextSelected: (String) -> Unit
) {

    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        value = textValue,
        onValueChange = {
            textValue = it
            onTextSelected(it)
        },
        label = {
            Text(text = labelValue)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.colorPrimary),
            focusedLabelColor = colorResource(R.color.colorPrimary),
            cursorColor = colorResource(R.color.colorPrimary),
            focusedContainerColor = bgColor,
            unfocusedContainerColor = bgColor,
        ),
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "profile")
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onTextSelected: (String) -> Unit
) {

    var passwordValue by remember { mutableStateOf("") }

    var passwordVisibility by remember { mutableStateOf(false) }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        value = passwordValue,
        onValueChange = {
            passwordValue = it
            onTextSelected(it)
        },
        label = {
            Text(text = labelValue)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.colorPrimary),
            focusedLabelColor = colorResource(R.color.colorPrimary),
            cursorColor = colorResource(R.color.colorPrimary),
            focusedContainerColor = bgColor,
            unfocusedContainerColor = bgColor,
        ),
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "image")
        },
        trailingIcon = {
            val iconPassword = if (passwordVisibility) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            val description = if (passwordVisibility) {
                stringResource(id = R.string.str_password_hide)
            } else {
                stringResource(id = R.string.str_password_show)
            }
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Icon(imageVector = iconPassword, contentDescription = description)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        maxLines = 1,
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        }
    )
}

@Composable
fun CheckBoxComponent(
    value: String,
    onPrivacyPolicyClick: () -> Unit,
    onTermsClick: () -> Unit,
) {

    var checkedState: Boolean by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = {
                checkedState = !checkedState
            }
        )
        SpannableTextLayout(
            value = value,
            onPrivacyPolicyClick = { onPrivacyPolicyClick() },
            onTermsClick = { onTermsClick() })

    }
}

@Suppress("DEPRECATION")
@Composable
fun SpannableTextLayout(
    value: String,
    onPrivacyPolicyClick: () -> Unit,
    onTermsClick: () -> Unit,
) {

    val initialText = "By continuing you agree to our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionsText = "Terms and Conditions of you"

    val annotatedString = buildAnnotatedString {
        append(initialText)

        pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
        withStyle(
            style = SpanStyle(
                color = primaryColor,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(privacyPolicyText)
        }

        pop()

        append(andText)

        pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
        withStyle(
            style = SpanStyle(
                color = primaryColor,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(termsAndConditionsText)
        }

        pop()
    }
    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = privacyPolicyText,
                start = offset,
                end = offset
            )
                .firstOrNull()?.let { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")
                    onPrivacyPolicyClick()
                }
            annotatedString.getStringAnnotations(
                tag = termsAndConditionsText,
                start = offset,
                end = offset
            )
                .firstOrNull()?.let { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")
                    onTermsClick()
                }
        }

    )


}

@Composable
fun ButtonComponent(
    value: String,
    onButtonClick: () -> Unit
) {

    Button(
        onClick = {
            onButtonClick.invoke()
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(secondaryColor, primaryColor)),
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

    }

}

@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = grayColor,
            thickness = 1.dp
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text = stringResource(R.string.str_or),
            style = TextStyle(
                fontSize = 18.sp,
                color = textColor
            )
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = grayColor,
            thickness = 1.dp
        )
    }
}

@Suppress("DEPRECATION")
@Composable
fun SpannableLoginTextLayout(
    tryingToLogin: Boolean = true,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {

    val initialText = if (tryingToLogin) "Already have an account? " else "Don't have an account? "
    val loginText = if (tryingToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)

        pushStringAnnotation(tag = loginText, annotation = loginText)
        withStyle(
            style = SpanStyle(
                color = primaryColor,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold
            )
        ) {
            append(loginText)
        }

        pop()

    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = loginText,
                start = offset,
                end = offset
            )
                .firstOrNull()?.let { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")
                    onLoginClick()
                }
        }

    )


}

@Composable
fun UnderLineTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        ),
        textDecoration = TextDecoration.Underline,
        color = colorResource(R.color.colorGray),
        textAlign = TextAlign.Center
    )

}