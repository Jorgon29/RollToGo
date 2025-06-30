package com.terraplanistas.rolltogo.data.remote.services

data class ClassResponse(
    val index: String,
    val name: String,
    val hit_die: Int,
    val proficiency_choices: List<ProficiencyChoice>,
    val proficiencies: List<ApiReference>,
    val saving_throws: List<ApiReference>,
    val starting_equipment: String,
    val class_levels: String,
    val subclasses: List<ApiReference>,
    val url: String
)


data class ApiReference(
    val index: String,
    val name: String,
    val url: String
)

data class ProficiencyChoice(
    val choose: Int,
    val type: String,
    val from: List<ApiReference>
)