package com.terraplanistas.rolltogo.ui.screens.actorCreation

interface ActorCreationStep {
    fun execute(context: ActorCreationContext, onNext: (ActorCreationContext) -> Unit)
}