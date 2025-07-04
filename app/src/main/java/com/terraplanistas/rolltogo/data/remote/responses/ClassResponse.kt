package com.terraplanistas.rolltogo.data.remote.services

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainClass
import com.terraplanistas.rolltogo.data.remote.responses.ContentResponse
import com.terraplanistas.rolltogo.data.remote.responses.SpellcastingResponse
import com.terraplanistas.rolltogo.data.remote.responses.SubclassResponse

data class ClassResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("content")
    val content: ContentResponse,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("hitDice")
    val hitDice: String,

    @SerializedName("hitPointsFirstLevel")
    val hitPointsFirstLevel: Int,

    @SerializedName("hitPointsPerLevel")
    val hitPointsPerLevel: String,

    @SerializedName("spellcasting")
    val spellcasting: List<SpellcastingResponse>?,

    @SerializedName("subclasses")
    val subclasses: List<SubclassResponse>?
)

fun ClassResponse.toDomain(grantId: String): DomainClass{
    return DomainClass(
        id = id,
        name = name,
        description = description?: "",
        grantId = grantId
    )
}