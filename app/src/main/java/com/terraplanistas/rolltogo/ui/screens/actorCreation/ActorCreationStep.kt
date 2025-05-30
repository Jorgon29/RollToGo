package com.terraplanistas.rolltogo.ui.screens.actorCreation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable

abstract class ActorCreationStep(
    private val viewModel: ActorCreationViewModel,
    private val nextStep: ActorCreationStep? = null,
    private val snackBarHostState: SnackbarHostState? = null
) {
    fun hasNext(): Boolean = nextStep != null

    fun getNext(): ActorCreationStep? {
        return nextStep
    }

    @Composable
    abstract fun Screen(context: ActorCreationContext)
}
