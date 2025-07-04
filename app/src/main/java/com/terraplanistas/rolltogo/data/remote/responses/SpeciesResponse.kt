package com.terraplanistas.rolltogo.data.remote.responses
import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainRace

data class SpeciesResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("content")
    val content: ContentResponse,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("creatureTypeEnum")
    val creatureTypeEnum: CreatureTypeEnum,

    @SerializedName("languages")
    val languages: String?,

    @SerializedName("sizeEnum")
    val sizeEnum: CreatureSizeEnum,

    @SerializedName("subspecies")
    val subspecies: List<SubspeciesResponse>?
)

fun SpeciesResponse.toDomain(): DomainRace{
    return DomainRace(
        id = id,
        name = name,
        description = description?:"",
        languages = languages?:"",
        size = sizeEnum,
        type = creatureTypeEnum
    )
}