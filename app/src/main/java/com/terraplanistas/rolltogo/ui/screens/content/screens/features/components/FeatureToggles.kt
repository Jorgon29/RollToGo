package com.terraplanistas.rolltogo.ui.screens.content.screens.features.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
 fun FeatureToggles(
    isMagical: Boolean,
    isPassive: Boolean,
    onMagicalChanged: (Boolean) -> Unit,
    onPassiveChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            "Propiedades de la feature",
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color(72, 94, 146)
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Switch(
                checked = isMagical,
                onCheckedChange = onMagicalChanged,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(109, 126, 168),
                    checkedTrackColor = Color(171, 181, 209),
                    uncheckedThumbColor = Color(189, 205, 221),
                    uncheckedTrackColor = Color(237, 238, 244)
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Feature Magica",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(30, 38, 81)
                )
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Switch(
                checked = isPassive,
                onCheckedChange = onPassiveChanged,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(109, 126, 168),
                    checkedTrackColor = Color(171, 181, 209),
                    uncheckedThumbColor = Color(189, 205, 221),
                    uncheckedTrackColor = Color(237, 238, 244)
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Feature Pasiva",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(30, 38, 81)
                )
            )
        }
    }
}