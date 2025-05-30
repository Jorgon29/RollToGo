package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.biographyStep

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiographyComnboBox(items: List<BiographyComboBoxItem>, changeSelected: (Int) -> Unit){
    var expandedMenu = rememberSaveable { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expandedMenu.value,
        onExpandedChange = {expandedMenu.value = it},
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text = {Text(text = item.text)},
                onClick = {changeSelected(item.id)},
            )
        }
    }
}