package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainFeats

data class FeatResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("content")
    val content: ContentResponse,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,
)

fun FeatResponse.toDomain(grantId: String): DomainFeats{
    return DomainFeats(
        id = id,
        description = description?: "",
        name = name,
        grantId = grantId
    )
}