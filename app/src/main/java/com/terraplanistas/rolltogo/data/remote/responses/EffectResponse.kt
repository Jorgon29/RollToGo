package com.terraplanistas.rolltogo.data.remote.responses
import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.ConditionTypeEnum
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum


data class EffectResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("conditionEnum")
    val conditionEnum: ConditionTypeEnum,

    @SerializedName("durationValue")
    val durationValue: Int,

    @SerializedName("durationUnit")
    val durationUnit: DurationUnitEnum
)
