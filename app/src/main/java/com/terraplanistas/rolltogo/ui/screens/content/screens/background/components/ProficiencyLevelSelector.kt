package com.terraplanistas.rolltogo.ui.screens.content.screens.background.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum

@Composable
fun ProficiencyLevelSelector(
    currentLevel: ProficiencyLevelEnum,
    onLevelSelected: (ProficiencyLevelEnum) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProficiencyLevelEnum.entries.forEach { level ->
            val isSelected = currentLevel == level
            val selectionColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
            val textColor = if (isSelected) Color.White else MaterialTheme.colorScheme.primary

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(selectionColor)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null // Elimina el efecto ripple para mejor rendimiento
                    ) { onLevelSelected(level) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (level) {
                        ProficiencyLevelEnum.PROFICIENT -> "P"
                        ProficiencyLevelEnum.EXPERTISE -> "E"
                        ProficiencyLevelEnum.HALF_PROFICIENT -> "H"
                        ProficiencyLevelEnum.NOT_PROFICIENT -> "N"
                    },
                    color = textColor,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}