package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.classStep

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationContext
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationViewModel
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionList
import com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList.SelectionListItem

class ClassStep(
    private val viewModel: ActorCreationViewModel,
    private val nextStep: ActorCreationStep? = null
) : ActorCreationStep(viewModel, nextStep) {

    override fun execute(
        context: ActorCreationContext,
        onNext: (ActorCreationContext) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    @Composable
    override fun Screen(context: ActorCreationContext, onNext: (ActorCreationContext) -> Unit) {

    }
    }


