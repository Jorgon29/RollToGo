package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum

data class InvocationCreateRequest(
    @SerializedName("creatureId")
    val creatureId: String,

    @SerializedName("durationValue")
    val durationValue: Int,

    @SerializedName("durationUnitEnum")
    val durationUnitEnum: DurationUnitEnum
)