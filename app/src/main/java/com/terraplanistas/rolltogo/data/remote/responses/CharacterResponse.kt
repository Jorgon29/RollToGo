package com.terraplanistas.rolltogo.data.remote.services

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.remote.responses.CreatureResponse

data class CharacterResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("creature")
    val creature: CreatureResponse,

    @SerializedName("race")
    val race: Int?,

    @SerializedName("characterClass")
    val characterClass: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("alignment")
    val alignment: Int?,

    @SerializedName("age")
    val age: String?,

    @SerializedName("ideals")
    val ideals: String?,

    @SerializedName("personality")
    val personality: String?,

    @SerializedName("flaws")
    val flaws: String?,

    @SerializedName("biography")
    val biography: String?,

    @SerializedName("appearance")
    val appearance: String?,

    @SerializedName("height")
    val height: String?,

    @SerializedName("weight")
    val weight: String?,

    @SerializedName("skinColor")
    val skinColor: String?,

    @SerializedName("hairColor")
    val hairColor: String?,

    @SerializedName("faith")
    val faith: String?,

    @SerializedName("eyeColor")
    val eyeColor: String?,

    @SerializedName("gender")
    val gender: Int?
)