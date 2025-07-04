package com.terraplanistas.rolltogo.ui.screens.actorCreation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class ActorCreationStep(
    private val viewModel: ActorCreationViewModel,
    private val nextStep: ActorCreationStep? = null,
    private val snackBarHostState: SnackbarHostState? = null,
) {
    private var _isReady by mutableStateOf(false)
    fun hasNext(): Boolean = nextStep != null

    fun getNext(): ActorCreationStep? {
        return nextStep
    }

    fun isDone(): Boolean{
        return _isReady
    }

    protected fun markReady(ready: Boolean) {
        _isReady = ready
    }

    @Composable
    abstract fun Screen(context: ActorCreationContext)
}
