package com.terraplanistas.rolltogo.ui.screens.actorCreation

import androidx.compose.runtime.Composable

abstract class ActorCreationStep(
    private val viewModel: ActorCreationViewModel,
    private val nextStep: ActorCreationStep? = null
) {
    abstract fun execute(
        context: ActorCreationContext,
        onNext: (ActorCreationContext) -> Unit
    )

    fun hasNext(): Boolean = nextStep == null
    @Composable
    abstract fun Screen(
        context: ActorCreationContext,
        onNext: (ActorCreationContext) -> Unit
    )
    fun getNext(): ActorCreationStep? {
        return nextStep
    }
}
