package com.terraplanistas.rolltogo.ui.screens.actorCreation

import com.terraplanistas.rolltogo.data.database.entities.CharacterEntity

data class ActorCreationContext(
    var race: Int? = null,
    var characterClass: Int? = null,
    var name: String? = null,
    var playstyle: Int? = null,
    var alignment: Int? = null,
    var age: Int? = null,
    var ideals: String? = null,
    var personality: String? = null,
    var flaws: String? = null,
    var biography: String? = null,
    var appearance: String? = null,
    var height: String? = null,
    var weight: String? = null,
    var skinColor: String? = null,
    var hairColor: String? = null,
    var faith: String? = null,
    var eyeColor: String? = null,
    var gender: Int? = null
)
