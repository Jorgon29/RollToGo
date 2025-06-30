package com.terraplanistas.rolltogo.data.remote.services

data class CharacterResponse(
    val id: String,
    val name: String,
    val race: String,
    val characterClass: String,
    val playstyle: String,
    val stats: Map<String, Int>
)