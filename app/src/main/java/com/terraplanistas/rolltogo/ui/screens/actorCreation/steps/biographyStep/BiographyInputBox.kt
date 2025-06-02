package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.biographyStep

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
@Composable
fun BiographyInputBox(
    placeholder: String,
    text: String,
    changeText: (String) -> Unit
) {
    val backgroundColor = MaterialTheme.colorScheme.secondary
    val textColor = Color.Black
    OutlinedTextField(
        value = text,
        onValueChange = {newValue: String -> changeText(newValue) },
        placeholder = {
            Text(
                text = placeholder,
                color = textColor.copy(alpha = 0.6f) // Subtle placeholder contrast
            )
        },
        textStyle = TextStyle(color = textColor),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            cursorColor = Color.Yellow,
            focusedBorderColor = Color.Yellow,
            unfocusedBorderColor = Color.Gray,
            focusedContainerColor = backgroundColor,
            unfocusedContainerColor = backgroundColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
    )
}

