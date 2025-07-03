package com.terraplanistas.rolltogo.ui.screens.content.screens.background.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.dataHolders.ProficiencyUI

@Composable
fun ProficienciesSelectionSection(
    proficiencies: List<ProficiencyUI>,
    selectedProficiencies: List<Map<String, String>>,
    onProficiencySelected: (ProficiencyUI, ProficiencyLevelEnum) -> Unit
) {
    Column {
        Text(
            text = "Proficiencias",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Cambiar a Column regular si la lista no es muy larga
        Column(
            modifier = Modifier
                .height(250.dp)
                .verticalScroll(rememberScrollState())
        ) {
            proficiencies.forEach { proficiency ->
                val currentLevel by remember(proficiency.name, selectedProficiencies) {
                    derivedStateOf {
                        selectedProficiencies
                            .find { it["name"] == proficiency.name }
                            ?.let {
                                ProficiencyLevelEnum.valueOf(it["type"] ?: "NOT_PROFICIENT")
                            } ?: ProficiencyLevelEnum.NOT_PROFICIENT
                    }
                }

                ProficiencyItem(
                    proficiency = proficiency,
                    currentLevel = currentLevel,
                    onLevelSelected = { level ->
                        onProficiencySelected(proficiency, level)
                    },

                )
            }
        }
    }
}