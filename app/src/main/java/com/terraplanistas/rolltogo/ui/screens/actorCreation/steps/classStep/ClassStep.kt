package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.classStep

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
import androidx.compose.material3.SnackbarHost
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
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationContext
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationViewModel
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionListItem
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionScreen

class ClassStep(
    private val viewModel: ActorCreationViewModel,
    private val nextStep: ActorCreationStep? = null,
    private val snackbarHostState: SnackbarHostState
) : ActorCreationStep(viewModel, nextStep) {
    @Composable
    override fun Screen(context: ActorCreationContext) {
            val associatedClasses = context.playstyle?.let { viewModel.getAssociatedClasses(it) }
            val unassociatedClasses = context.playstyle?.let { viewModel.getUnassociatedClasses(it) }
            val mainClasses = remember { mutableStateOf(emptyList<SelectionListItem>()) }

        val selectedClass = rememberSaveable { mutableIntStateOf(-1) }
        val extendedMenu = rememberSaveable { mutableStateOf(false) }

        LaunchedEffect(Unit){
            associatedClasses?.let {
                mainClasses.value = associatedClasses.map { characterClass: CharacterClass ->
                    SelectionListItem(
                        icon = characterClass.icon,
                        name = characterClass.name,
                        description = characterClass.description,
                        id = characterClass.id
                    )
                }
            }
        }

        LaunchedEffect(selectedClass.intValue) {
                markReady(selectedClass.intValue != -1)
        }

        fun selectClass(selectedClassNew: Int){
            selectedClass.intValue = selectedClassNew
            context.characterClass = selectedClass.intValue
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {

            Text(stringResource(R.string.actor_creation_class_title), fontWeight = FontWeight.Bold)
            Text(stringResource(R.string.actor_creation_class_subtitle), fontWeight = FontWeight.Bold)

            SelectionScreen(
                items = mainClasses.value,
                snackbarHostState = snackbarHostState,
                setSelected = {newClass -> selectClass(newClass)},
                selected = selectedClass.intValue
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
                    unassociatedClasses?.let {
                        unassociatedClasses.forEach{ unassociatedClass: CharacterClass ->
                            DropdownMenuItem(
                                text = { Text(unassociatedClass.name) },
                                onClick = {
                                    selectClass(unassociatedClass.id)
                                    extendedMenu.value = false
                                          },
                                leadingIcon = { Image(imageVector = unassociatedClass.icon, unassociatedClass.name + ": " + unassociatedClass.description) }
                            )
                        }
                    }
                }
            }


        }


    }

}


