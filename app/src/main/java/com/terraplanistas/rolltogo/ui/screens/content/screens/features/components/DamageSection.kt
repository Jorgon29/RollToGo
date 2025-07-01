package com.terraplanistas.rolltogo.ui.screens.content.screens.features.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
 fun DamageSection(
    damage: String,
    damageType: String,
    onDamageChanged: (String) -> Unit,
    onDamageTypeSelected: (String) -> Unit
) {
    Column {
        Text("Información del daño", style = MaterialTheme.typography.labelMedium)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = damage,
            onValueChange = onDamageChanged,
            label = { Text("Daño (E.j, 1d6)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        DamageTypeDropdown(
            currentValue = damageType,
            onValueSelected = onDamageTypeSelected,
            modifier = Modifier.fillMaxWidth()
        )
    }
}