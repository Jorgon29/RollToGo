package com.terraplanistas.rolltogo.ui.screens.actorCreation

import com.terraplanistas.rolltogo.data.model.Playstyle

data class ActorCreationContext(
    var race: String? = null,
    var characterClass: String? = null,
    var name: String? = null,
    var playstyle: Playstyle? = null
)
