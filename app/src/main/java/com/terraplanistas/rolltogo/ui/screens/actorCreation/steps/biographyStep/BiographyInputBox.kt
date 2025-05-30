package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.biographyStep

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BiographyInputBox(placeholder: String,text: String, changeText: (String) -> Unit){
    OutlinedTextField(
        value = text,
        onValueChange = {changeText(it)},
        placeholder = { Text(placeholder) }
    )

}
