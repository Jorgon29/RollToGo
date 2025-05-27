package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.raceStep

import androidx.compose.runtime.Composable
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationContext
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationViewModel

class RaceStep(
    private val viewModel: ActorCreationViewModel,
    private val nextStep: ActorCreationStep? = null
): ActorCreationStep(nextStep = nextStep, viewModel = viewModel) {
    override fun execute(
        context: ActorCreationContext,
        onNext: (ActorCreationContext) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    @Composable
    override fun Screen(
        context: ActorCreationContext,
        onNext: (ActorCreationContext) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}