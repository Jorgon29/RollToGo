package com.terraplanistas.rolltogo.ui.screens.content.screens.background.components

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.dataHolders.ProficiencyUI

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProficiencyItem(
    proficiency: ProficiencyUI,
    currentLevel: ProficiencyLevelEnum,
    onLevelSelected: (ProficiencyLevelEnum) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                    }
                }
        ) {
            Text(
                text = proficiency.name,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Habilidad: ${proficiency.ability.uppercase()}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.alpha(0.8f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            ProficiencyLevelSelector(
                currentLevel = currentLevel,
                onLevelSelected = onLevelSelected,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}