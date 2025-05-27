package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.playstyleStep
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.Playstyle
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationContext
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationViewModel
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionList
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionListItem

class PlaystyleStep(
    private val viewModel: ActorCreationViewModel,
    private val nextStep: ActorCreationStep? = null
) : ActorCreationStep(viewModel, nextStep) {
    override fun execute(
        context: ActorCreationContext,
        onNext: (ActorCreationContext) -> Unit
    ) {
    }

    @Composable
    override fun Screen(context: ActorCreationContext, onNext: (ActorCreationContext) -> Unit) {
        var playstyles = viewModel.getPlaystyles()
        val selectionList: List<SelectionListItem> = playstyles.map { SelectionListItem(
            icon = it.icon,
            name = it.description,
            id = it.id
        ) }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(stringResource(R.string.actor_creation_playstyle_title), fontSize = 32.sp)
            Spacer(Modifier.height(8.dp))
            Text(stringResource(R.string.actor_creation_playstyle_subtitle), fontSize = 8.sp)
            SelectionList(selectionList)
        }
    }
}
