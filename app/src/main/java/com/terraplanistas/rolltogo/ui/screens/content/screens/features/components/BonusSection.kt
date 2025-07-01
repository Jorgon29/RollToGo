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
 fun BonusSection(
    bonus: String,
    bonusType: String,
    onBonusChanged: (String) -> Unit,
    onBonusTypeChanged: (String) -> Unit
) {
    Column {
        Text("Información de bonus", style = MaterialTheme.typography.labelMedium)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = bonus,
            onValueChange = onBonusChanged,
            label = { Text("Bonus (ej. +2") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        BonustypeField(bonusType = bonusType, onBonusTypeChanged = onBonusTypeChanged, modifier = Modifier.fillMaxWidth())




    }
}

