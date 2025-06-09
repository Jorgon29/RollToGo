package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.biographyStep

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.CharacterAlignment
import com.terraplanistas.rolltogo.data.model.CharacterGender
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationContext
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationViewModel

class BiographyStep(
    private val viewModel: ActorCreationViewModel,
    private val nextStep: ActorCreationStep? = null
) : ActorCreationStep(viewModel, nextStep) {

    @Composable
    override fun Screen(context: ActorCreationContext) {

        val alignments = viewModel.getAlignments()
        val genders = viewModel.getGenders()
        var name = rememberSaveable { mutableStateOf("") }
        var age = rememberSaveable { mutableStateOf("") }
        var alignment = rememberSaveable { mutableIntStateOf(0) }
        var alignmentDisplay = rememberSaveable { mutableStateOf("") }
        var gender = rememberSaveable { mutableIntStateOf(0) }
        var genderDisplay = rememberSaveable { mutableStateOf("") }
        var ideals = rememberSaveable { mutableStateOf("") }
        var personality = rememberSaveable { mutableStateOf("") }
        var flaws = rememberSaveable { mutableStateOf("") }
        var biography = rememberSaveable { mutableStateOf("") }
        var appearance = rememberSaveable { mutableStateOf("") }


        Text(stringResource(R.string.actor_creation_biography_title), fontWeight = FontWeight.Bold)
        Text(stringResource(R.string.actor_creation_biography_subtitle))

        LaunchedEffect(
            name.value,
            age.value,
            alignment.intValue,
            gender.intValue,
            ideals.value,
            personality.value,
            flaws.value,
            biography.value,
            appearance.value
        ) {
/*
           Log.d("Biography", "Name: "+name.value + " Valid? " + name.value.isNotBlank())
           Log.d("Biography", "Age: "+age.intValue + " Valid? " + (age.intValue != -1))
            Log.d("Biography", "Alignment: "+alignment.intValue + " Valid? " + (alignment.intValue != -1))
            Log.d("Biography", "Gender: "+gender.intValue + " Valid? " + (age.intValue != -1))
            Log.d("Biography", "Ideals: "+ideals.value)
            Log.d("Biography", "Personality: "+personality.value)
            Log.d("Biography", "Flaws: "+biography.value)
            Log.d("Biography", "Appearance: "+appearance.value)
*/

            context.name = name.value
            context.age = age.value
            context.alignment = alignment.intValue
            context.gender = gender.intValue
            context.ideals = ideals.value
            context.personality = personality.value
            context.flaws = flaws.value
            context.biography = biography.value
            context.appearance = appearance.value
            markReady(
                name.value.isNotBlank() &&
                        !age.value.isEmpty() &&
                        alignment.intValue != 0 &&
                        gender.intValue != 0 &&
                        ideals.value.isNotBlank() &&
                        personality.value.isNotBlank() &&
                        flaws.value.isNotBlank() &&
                        biography.value.isNotBlank() &&
                        appearance.value.isNotBlank()
            )
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(stringResource(R.string.actor_creation_biography_name) + "*")
            BiographyInputBox(
                placeholder = stringResource(R.string.actor_creation_biography_name),
                text = name.value,
                changeText = { name.value = it }
            )
            Text(stringResource(R.string.actor_creation_biography_age) + "*")
            BiographyInputBox(
                placeholder = stringResource(R.string.actor_creation_biography_age),
                text = age.value,
                changeText = { input: String -> age.value = input },
                isNumeric = true
            )
            Text(stringResource(R.string.actor_creation_biography_alignment) + "*")
            BiographyComboBox(
                items = alignments.map { alignment: CharacterAlignment ->
                    BiographyComboBoxItem(
                        text = alignment.name,
                        id = alignment.id
                    )
                },
                changeSelected = {
                    alignment.intValue = it
                    alignmentDisplay.value = alignments[it - 1].name
                },
                selectedItem = alignmentDisplay.value,
                label = stringResource(R.string.actor_creation_biography_alignment)
            )

            Text(stringResource(R.string.actor_creation_biography_gender) + "*")
            BiographyComboBox(
                items = genders.map { gender: CharacterGender ->
                    BiographyComboBoxItem(
                        text = gender.name,
                        id = gender.id
                    )
                },
                selectedItem = genderDisplay.value,
                changeSelected = {
                    gender.intValue = it
                    genderDisplay.value = genders[it - 1].name
                },
                label = stringResource(R.string.actor_creation_biography_gender)
            )

            Text(stringResource(R.string.actor_creation_biography_ideals) + "*")
            BiographyInputBox(
                placeholder = stringResource(R.string.actor_creation_biography_ideals),
                text = ideals.value,
                changeText = { ideals.value = it }
            )

            Text(stringResource(R.string.actor_creation_biography_personality) + "*")
            BiographyInputBox(
                placeholder = stringResource(R.string.actor_creation_biography_personality),
                text = personality.value,
                changeText = { personality.value = it }
            )

            Text(stringResource(R.string.actor_creation_biography_flaws) + "*")
            BiographyInputBox(
                placeholder = stringResource(R.string.actor_creation_biography_flaws),
                text = flaws.value,
                changeText = { flaws.value = it }
            )

            Text(stringResource(R.string.actor_creation_biography_biography) + "*")
            BiographyInputBox(
                placeholder = stringResource(R.string.actor_creation_biography_biography),
                text = biography.value,
                changeText = { biography.value = it }
            )

            Text(stringResource(R.string.actor_creation_biography_appearance) + "*")
            BiographyInputBox(
                placeholder = stringResource(R.string.actor_creation_biography_appearance),
                text = appearance.value,
                changeText = { appearance.value = it }
            )
        }
    }
}