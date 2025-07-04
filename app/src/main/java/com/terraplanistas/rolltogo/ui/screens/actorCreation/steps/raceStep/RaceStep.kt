package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.raceStep

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
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
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Minus
import com.composables.icons.lucide.Plus
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.CharacterClass
import com.terraplanistas.rolltogo.data.model.CharacterRace
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationContext
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationViewModel
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionListItem
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionScreen

class RaceStep(
    private val viewModel: ActorCreationViewModel,
    private val nextStep: ActorCreationStep? = null,
    private val snackbarHostState: SnackbarHostState
): ActorCreationStep(nextStep = nextStep, viewModel = viewModel) {

    @Composable
    override fun Screen(
        context: ActorCreationContext
    ) {
        val associatedRaces = context.characterClass?.let {viewModel.getAssociatedRaces(it)}
        val unassociatedRaces = context.characterClass?.let {viewModel.getUnassociatedRaces(it)}
        val mainRaces = rememberSaveable { mutableStateOf(emptyList<SelectionListItem>()) }

        val selectedRace = rememberSaveable { mutableIntStateOf(-1) }
        val extendedMenu = rememberSaveable { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            associatedRaces?.let {
                mainRaces.value = associatedRaces.map { race: CharacterRace ->
                    SelectionListItem(
                        icon = race.icon,
                        name = race.name,
                        description = race.description,
                        id = race.id
                    )
                }
            }
        }

        LaunchedEffect(selectedRace.intValue) {
                markReady(selectedRace.intValue != -1)
        }

        fun selectRace(raceId: Int){
            selectedRace.intValue = raceId
            context.race = raceId
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(stringResource(R.string.actor_creation_race_title), fontWeight = FontWeight.Bold)
            Text(stringResource(R.string.actor_creation_race_subtitle))

            SelectionScreen(
                items = mainRaces.value,
                snackbarHostState = snackbarHostState,
                setSelected = { newlySelected ->
                    selectRace(newlySelected)
                    context.race = newlySelected
                },
                selected = selectedRace.intValue
            )

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.actor_creation_class_more_classes))

                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = { extendedMenu.value = true }
                ) {
                    Image(imageVector = if (extendedMenu.value) Lucide.Minus else Lucide.Plus, stringResource(R.string.extend_menu))
                }

                DropdownMenu(
                    expanded = extendedMenu.value,
                    onDismissRequest = {extendedMenu.value = false}
                ) {
                    unassociatedRaces?.let {
                        unassociatedRaces.forEach{ unassociatedRace: CharacterRace ->
                            DropdownMenuItem(
                                text = { Text(unassociatedRace.name) },
                                onClick = {
                                    selectRace(unassociatedRace.id)
                                    extendedMenu.value = false
                                },
                                leadingIcon = { Image(imageVector = unassociatedRace.icon, unassociatedRace.name + ": " + unassociatedRace.description) }
                            )
                        }
                    }
                }
            }
        }

    }

}