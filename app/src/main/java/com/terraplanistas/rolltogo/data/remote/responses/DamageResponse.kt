package com.terraplanistas.rolltogo.data.remote.responses
import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.CastingTimeUnitEnum
import com.terraplanistas.rolltogo.data.enums.DamageTypeEnum


data class DamageResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("content")
    val content: ContentResponse,

    @SerializedName("damageFormula")
    val damageFormula: String,

    @SerializedName("damageTypeEnum")
    val damageTypeEnum: DamageTypeEnum,

    @SerializedName("repeat")
    val repeat: Boolean,

    @SerializedName("repeatTimeValue")
    val repeatTimeValue: Int?,

    @SerializedName("repeatTimeUnit")
    val repeatTimeUnit: CastingTimeUnitEnum?
)