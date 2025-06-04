package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.characteristicsStep

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationContext
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationViewModel
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionList
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionListItem
import com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.biographyStep.BiographyInputBox
import kotlin.math.sin

class CharacteristicsStep(
    private val viewModel: ActorCreationViewModel,
    private val nextStep: ActorCreationStep? = null
) : ActorCreationStep(viewModel, nextStep) {

    @Composable
    override fun Screen(context: ActorCreationContext) {
        var height = rememberSaveable { mutableStateOf("") }
        var weight = rememberSaveable { mutableStateOf("") }
        var skin = rememberSaveable { mutableStateOf("") }
        var hair = rememberSaveable { mutableStateOf("") }
        var faith = rememberSaveable { mutableStateOf("") }
        var eyes = rememberSaveable { mutableStateOf("") }

        LaunchedEffect(
            height.value,
            weight.value,
            skin.value,
            hair.value,
            faith.value,
            eyes.value
        ) {
            context.height = height.value
            context.weight = weight.value
            context.skinColor = skin.value
            context.hairColor = hair.value
            context.faith = faith.value
            context.eyeColor = eyes.value

            markReady(height.value.isNotBlank() &&
                    weight.value.isNotBlank() &&
                    skin.value.isNotBlank() &&
                    hair.value.isNotBlank() &&
                    faith.value.isNotBlank() &&
                    eyes.value.isNotBlank())
        }

        Column(
            modifier = Modifier.padding(horizontal = 16.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {

            Text(stringResource(R.string.actor_creation_characteristics_title), fontWeight = FontWeight.Bold)
            Text(stringResource(R.string.actor_creation_characteristics_subtitle))

            Text(stringResource(R.string.actor_creation_characteristics_height)+"*")
            BiographyInputBox(
                placeholder = stringResource(R.string.actor_creation_characteristics_height),
                text = height.value,
                changeText = {height.value = it}
            )

            Text(stringResource(R.string.actor_creation_characteristics_weight)+"*")
            BiographyInputBox(
                placeholder = stringResource(R.string.actor_creation_characteristics_weight),
                text = weight.value,
                changeText = {weight.value = it}
            )

            Text(stringResource(R.string.actor_creation_characteristics_skin)+"*")
            BiographyInputBox(
                placeholder = stringResource(R.string.actor_creation_characteristics_skin),
                text = skin.value,
                changeText = {skin.value = it}
            )

            Text(stringResource(R.string.actor_creation_characteristics_hair)+"*")
            BiographyInputBox(
                placeholder = stringResource(R.string.actor_creation_characteristics_hair),
                text = hair.value,
                changeText = {hair.value = it}
            )

            Text(stringResource(R.string.actor_creation_characteristics_faith)+"*")
            BiographyInputBox(
                placeholder = stringResource(R.string.actor_creation_characteristics_faith),
                text = faith.value,
                changeText = {faith.value = it}
            )

            Text(stringResource(R.string.actor_creation_characteristics_eyes)+"*")
            BiographyInputBox(
                placeholder = stringResource(R.string.actor_creation_characteristics_eyes),
                text = eyes.value,
                changeText = {eyes.value = it}
            )
        }

    }
}