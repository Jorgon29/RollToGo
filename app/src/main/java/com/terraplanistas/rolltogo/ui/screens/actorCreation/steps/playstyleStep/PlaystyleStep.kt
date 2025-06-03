package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.playstyleStep
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationContext
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationViewModel
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionList
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionListItem
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionScreen

class PlaystyleStep(
    private val viewModel: ActorCreationViewModel,
    private val nextStep: ActorCreationStep? = null,
    private val snackBarHostState: SnackbarHostState
) : ActorCreationStep(viewModel, nextStep, snackBarHostState) {

    @Composable
    override fun Screen(context: ActorCreationContext) {
        val selected = rememberSaveable { mutableIntStateOf(-1) }
        var playstyles = viewModel.getPlaystyles()
        val selectionList: List<SelectionListItem> = playstyles.map { SelectionListItem(
            icon = it.icon,
            name = it.description,
            id = it.id
        ) }

        LaunchedEffect(selected.intValue) {
            markReady(selected.intValue != -1)
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(stringResource(R.string.actor_creation_playstyle_title), fontSize = 32.sp)
            Spacer(Modifier.height(8.dp))
            Text(stringResource(R.string.actor_creation_playstyle_subtitle), fontSize = 16.sp)
            SelectionScreen(
                items = selectionList,
                snackBarHostState,
                setSelected = {selection -> selected.intValue = selection
                    context.playstyle = selection
                },
                selected = selected.intValue
            )

        }

    }
}
