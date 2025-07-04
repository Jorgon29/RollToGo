package com.terraplanistas.rolltogo.ui.screens.characterScreen.SecondaryScreens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainCharacter
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainSpell
import com.terraplanistas.rolltogo.helpers.Resource
import com.terraplanistas.rolltogo.ui.layout.boxes.basicTitle.BasicTitle
import com.terraplanistas.rolltogo.ui.layout.boxes.cateogoryBox.CategoryBox
import com.terraplanistas.rolltogo.ui.screens.characterScreen.CharacterScreenViewModel

@Composable
fun SpellsScreen(id: String,viewModel: CharacterScreenViewModel = viewModel(factory = CharacterScreenViewModel.Factory)){
    val characterResource by viewModel.characterResource.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCharacter(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(listOf<Color>(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.surfaceContainer),
                    radius = 1300f
                )),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BasicTitle(title = stringResource(R.string.actor_screen_spells))

        Spacer(Modifier.height(16.dp))

        when (characterResource) {
            is Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Error -> {
                Text("Error: ${(characterResource as Resource.Error).message}", color = Color.Red)
            }
            is Resource.Success -> {
                val character = (characterResource as Resource.Success<DomainCharacter>).data

                character?.let {

                    LazyColumn(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        items(character.spells) { spell ->
                            ExpandableSpellCard(spell)
                        }
                    }

                } ?: run {

                    Text("Error: Datos del personaje no disponibles.")
                }
            }
        }
    }
}


@Composable
fun ExpandableSpellCard(spell: DomainSpell, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .fillMaxWidth(0.8f),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            CategoryBox(
                title = spell.name,
                content = spell.description,
                onClick = { expanded = !expanded }
            )

            if (expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Level ${spell.level.name.replace("LEVEL_", "").lowercase().capitalize()} - ${spell.spellSchoolEnum.name.capitalize()}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(Modifier.height(4.dp))

                    DetailRow(label = "Casting Time", value = spell.castingTime)
                    DetailRow(label = "Range", value = spell.range)
                    DetailRow(label = "Duration", value = spell.duration)
                    DetailRow(label = "Components", value = spell.components)

                    if (spell.spellMaterials.isNotEmpty()) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Materials:",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            spell.spellMaterials.forEach { material ->
                                Text(
                                    text = "${material.item.name}${if (material.consumed) " (Consumed)" else ""}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    }

                    if (spell.isRitual) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Can be cast as a ritual.",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = spell.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    if (value.isNotBlank()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "$label:",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Spacer(Modifier.height(4.dp))
    }
}