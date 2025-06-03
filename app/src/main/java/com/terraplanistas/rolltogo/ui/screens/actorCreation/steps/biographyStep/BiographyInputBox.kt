package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.biographyStep

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
@Composable
fun BiographyInputBox(
    placeholder: String,
    text: String,
    changeText: (String) -> Unit,
    isNumeric: Boolean = false
) {
    val backgroundColor = MaterialTheme.colorScheme.secondary
    val textColor = Color.Black

    OutlinedTextField(
        value = text,
        onValueChange = { newValue ->
            if (!isNumeric || newValue.isEmpty() || newValue.matches(Regex("^\\d+\$"))) {
                changeText(newValue)
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = if (isNumeric) KeyboardType.Number else KeyboardType.Text
        ),
        placeholder = {
            Text(
                text = placeholder,
                color = textColor.copy(alpha = 0.6f)
            )
        },
        textStyle = TextStyle(color = textColor),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            cursorColor = MaterialTheme.colorScheme.surface,
            focusedBorderColor = MaterialTheme.colorScheme.surface,
            unfocusedBorderColor = Color.Gray,
            focusedContainerColor = backgroundColor,
            unfocusedContainerColor = backgroundColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
    )
}

