package com.terraplanistas.rolltogo.ui.screens.actorCreation

import com.terraplanistas.rolltogo.data.model.Playstyle

data class ActorCreationContext(
    var race: Int? = null,
    var characterClass: Int? = null,
    var name: String? = null,
    var playstyle: Int? = null
)
