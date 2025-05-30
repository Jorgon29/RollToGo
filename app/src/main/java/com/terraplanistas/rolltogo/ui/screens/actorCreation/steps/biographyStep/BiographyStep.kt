package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.biographyStep

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    }
}