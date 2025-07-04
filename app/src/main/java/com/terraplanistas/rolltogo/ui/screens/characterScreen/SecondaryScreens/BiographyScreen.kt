package com.terraplanistas.rolltogo.ui.screens.characterScreen.SecondaryScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainCharacter
import com.terraplanistas.rolltogo.helpers.Resource
import com.terraplanistas.rolltogo.ui.layout.boxes.basicTitle.BasicTitle
import com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.biographyStep.BiographyInputBox
import com.terraplanistas.rolltogo.ui.screens.characterScreen.CharacterScreenViewModel

@Composable
fun BiographyScreen(id: String,viewModel: CharacterScreenViewModel = viewModel(factory = CharacterScreenViewModel.Factory)){
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

        BasicTitle(stringResource(R.string.actor_screen_biography))

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

                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp)
                    ) {
                        Spacer(Modifier.height(64.dp))

                        Text(stringResource(R.string.actor_creation_biography_name))
                        BiographyInputBox(text = character.name, placeholder = "", changeText = {}, readOnly = true)

                        Text(stringResource(R.string.actor_creation_biography_age))
                        BiographyInputBox(text = character.age, placeholder = "", changeText = {}, readOnly = true)

                        Text(stringResource(R.string.actor_creation_biography_alignment))
                        BiographyInputBox(text = character.alignment.value, placeholder = "", changeText = {}, readOnly = true)

                        Text(stringResource(R.string.actor_creation_biography_gender))
                        BiographyInputBox(text = character.gender, placeholder = "", changeText = {}, readOnly = true)

                        Text(stringResource(R.string.actor_creation_characteristics_height))
                        BiographyInputBox(text = character.height, placeholder = "", changeText = {}, readOnly = true)

                        Text(stringResource(R.string.actor_creation_characteristics_weight))
                        BiographyInputBox(text = character.weight, placeholder = "", changeText = {}, readOnly = true)

                        Text(stringResource(R.string.actor_creation_characteristics_skin))
                        BiographyInputBox(text = character.skin, placeholder = "", changeText = {}, readOnly = true)

                        Text(stringResource(R.string.actor_creation_characteristics_hair))
                        BiographyInputBox(text = character.hair, placeholder = "", changeText = {}, readOnly = true)

                        Text(stringResource(R.string.actor_creation_characteristics_faith))
                        BiographyInputBox(text = character.faith, placeholder = "", changeText = {}, readOnly = true)

                        Text(stringResource(R.string.actor_creation_characteristics_eyes))
                        BiographyInputBox(text = character.eyes, placeholder = "", changeText = {}, readOnly = true)

                        Text(stringResource(R.string.actor_creation_biography_appearance))
                        BiographyInputBox(text = character.appearance, placeholder = "", changeText = {}, readOnly = true)

                    }
                } ?: run {
                    Text("Error: Datos del personaje no disponibles.")
                }
            }
        }
    }

}
