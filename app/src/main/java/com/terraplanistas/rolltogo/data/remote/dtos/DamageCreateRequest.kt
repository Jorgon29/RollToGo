package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.CastingTimeUnitEnum
import com.terraplanistas.rolltogo.data.enums.DamageTypeEnum
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum

data class DamageCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("damageFormula")
    val damageFormula: String,

    @SerializedName("damageTypeEnum")
    val damageTypeEnum: DamageTypeEnum,

    @SerializedName("repeat")
    val repeat: Boolean,

    @SerializedName("repeatTimeValue")
    val repeatTimeValue: Int?,

    @SerializedName("repeatTimeUnit")
    val repeatTimeUnit: DurationUnitEnum?
)