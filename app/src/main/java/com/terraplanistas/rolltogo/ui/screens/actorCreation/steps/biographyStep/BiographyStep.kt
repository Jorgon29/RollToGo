package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.biographyStep

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.CharacterAlignment
import com.terraplanistas.rolltogo.data.model.CharacterRace
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationContext
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationViewModel
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionList
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionListItem

class BiographyStep(
    private val viewModel: ActorCreationViewModel,
    private val nextStep: ActorCreationStep? = null
) : ActorCreationStep(viewModel, nextStep) {

    @Composable
    override fun Screen(context: ActorCreationContext) {

        val alignments = viewModel.getAlignments()
        var name = rememberSaveable { mutableStateOf("") }
        var age = rememberSaveable { mutableIntStateOf(0) }
        var alignment = rememberSaveable { mutableIntStateOf(0) }
        var gender = rememberSaveable { mutableIntStateOf(0) }
        var ideals = rememberSaveable { mutableStateOf("") }
        var personality = rememberSaveable { mutableStateOf("") }
        var flaws = rememberSaveable { mutableStateOf("") }
        var biography = rememberSaveable { mutableStateOf("") }
        var appearance = rememberSaveable { mutableStateOf("") }

        BiographyInputBox(
            placeholder = stringResource(R.string.actor_creation_biography_name),
            text = name.value,
            changeText = {name.value = it}
        )

        BiographyInputBox(
            placeholder = stringResource(R.string.actor_creation_biography_age),
            text = age.intValue.toString(),
            changeText = { input: Int -> age.intValue = input} as (String) -> Unit,
        )

        BiographyComnboBox(
            items = alignments.map { alignment: CharacterAlignment ->
                BiographyComboBoxItem(
                    text = alignment.name,
                    id = alignment.id
                )
            },
            changeSelected = { alignment.intValue = it }
        )

    }
}