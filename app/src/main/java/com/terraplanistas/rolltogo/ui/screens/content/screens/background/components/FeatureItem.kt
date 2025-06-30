package com.terraplanistas.rolltogo.ui.screens.content.screens.background.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.dataHolders.FeatureUI

@Composable
fun FeatureItem(
    feature: FeatureUI,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
        ),
        onClick = onSelected
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                feature.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                feature.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}