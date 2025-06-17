package com.terraplanistas.rolltogo.ui.screens.characterScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainCharacter
import com.terraplanistas.rolltogo.helpers.Resource
import com.terraplanistas.rolltogo.ui.layout.boxes.basicTitle.BasicTitle

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainAbility
import com.terraplanistas.rolltogo.R
@Composable
fun CharacterScreen(
    navController: NavController? = null,
    characterId: String,
    viewModel: CharacterScreenViewModel = viewModel(factory = CharacterScreenViewModel.Factory)
) {
    val characterResource by viewModel.characterResource.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadDummyCharacter()
    }
    navController?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(
                        Brush.radialGradient(listOf<Color>(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.surfaceContainer),
                            radius = 1300f
                        )),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(16.dp))

                when (characterResource) {
                    is Resource.Loading -> {
                        CircularProgressIndicator()
                        Text("Cargando personaje...", modifier = Modifier.padding(top = 8.dp))
                    }
                    is Resource.Error -> {
                        Text("Error: ${(characterResource as Resource.Error).message}", color = Color.Red)
                    }
                    is Resource.Success -> {
                        val character = (characterResource as Resource.Success<DomainCharacter>).data

                        character?.let {
                            BasicTitle(title = it.name)
                            Spacer(Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    StatDisplay(
                                        ability = it.abilities.find { a -> a.abilityEnum == AbilityTypeEnum.STRENGTH },
                                        statName = "STR"
                                    )
                                    Spacer(Modifier.height(8.dp))
                                    StatDisplay(
                                        ability = it.abilities.find { a -> a.abilityEnum == AbilityTypeEnum.DEXTERITY },
                                        statName = "DEX"
                                    )
                                    Spacer(Modifier.height(8.dp))
                                    StatDisplay(
                                        ability = it.abilities.find { a -> a.abilityEnum == AbilityTypeEnum.CONSTITUTION },
                                        statName = "CON"
                                    )
                                }

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    StatDisplay(
                                        ability = it.abilities.find { a -> a.abilityEnum == AbilityTypeEnum.INTELLIGENCE },
                                        statName = "INT"
                                    )
                                    Spacer(Modifier.height(8.dp))
                                    StatDisplay(
                                        ability = it.abilities.find { a -> a.abilityEnum == AbilityTypeEnum.WISDOM },
                                        statName = "WIS"
                                    )
                                    Spacer(Modifier.height(8.dp))
                                    StatDisplay(
                                        ability = it.abilities.find { a -> a.abilityEnum == AbilityTypeEnum.CHARISMA },
                                        statName = "CHA"
                                    )
                                }
                            }
                            Spacer(Modifier.height(24.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ValueOnImage(
                                    imageResId = R.drawable.group6,
                                    value = it.ac.toString(),
                                    contentDescription = "Armadura",
                                    valueColor = Color.Black
                                )
                                ValueOnImage(
                                    imageResId = R.drawable.group6,
                                    value = it.hp.toString(),
                                    contentDescription = "Puntos de Golpe",
                                    valueColor = Color.Red
                                )
                            }
                            Spacer(Modifier.height(24.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val initiativeModifier =
                                    it.abilities.find { a -> a.abilityEnum == AbilityTypeEnum.DEXTERITY }?.modifier ?: 0
                                ValueOnImage(
                                    imageResId = R.drawable.d20,
                                    value = if (initiativeModifier >= 0) "+$initiativeModifier" else "$initiativeModifier",
                                    contentDescription = "Iniciativa",
                                    valueColor = Color.Blue
                                )
                            }
                        } ?: run {

                            Text("Error: Datos del personaje no disponibles.")
                        }
                    }
                }
            }
    }
}

@Composable
fun StatDisplay(ability: DomainAbility?, statName: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = statName, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Spacer(Modifier.height(4.dp))
        ValueOnImage(
            imageResId = R.drawable.stat,
            value = ability?.value?.toString() ?: "N/A",
            contentDescription = "$statName Stat",
            valueColor = Color.Black
        )
    }
}

@Composable
fun ValueOnImage(
    imageResId: Int,
    value: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    valueColor: Color = Color.Unspecified,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge
) {
    Box(
        modifier = modifier.size(80.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = value,
            color = valueColor,
            style = textStyle
        )
    }
}
