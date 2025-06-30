package com.terraplanistas.rolltogo.ui.screens.content.screens.background.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.dataHolders.FeatureUI

@Composable
fun FeaturesSelectionSection(
    features: List<FeatureUI>,
    selectedFeatureIds: List<String>,
    onFeatureSelected: (FeatureUI) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            "Rasgos Especiales",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn {
            if (features.isNotEmpty()) {
                items(features) { feature ->
                    FeatureItem(
                        feature = feature,
                        isSelected = selectedFeatureIds.contains(feature.id),
                        onSelected = { onFeatureSelected(feature) }
                    )
                }
            } else {
                item {
                    Text(
                        "No hay rasgos especiales disponibles.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}