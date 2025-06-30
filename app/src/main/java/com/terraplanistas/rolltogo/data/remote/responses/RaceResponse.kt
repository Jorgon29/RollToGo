package com.terraplanistas.rolltogo.data.remote.services

data class RaceResponse(
    val index: String,
    val name: String,
    val speed: Int,
    val ability_bonuses: List<AbilityBonus>,
    val alignment: String,
    val age: String,
    val size: String,
    val size_description: String,
    val starting_proficiencies: List<ApiReference>,
    val languages: List<ApiReference>,
    val language_desc: String,
    val traits: List<ApiReference>,
    val subraces: List<ApiReference>,
    val url: String
)


data class AbilityBonus(
    val ability_score: ApiReference,
    val bonus: Int
)

data class ApiReference(
    val index: String,
    val name: String,
    val url: String
)