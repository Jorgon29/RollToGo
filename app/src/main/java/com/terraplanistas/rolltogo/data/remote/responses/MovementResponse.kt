package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.MovementTypeEnum
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum

data class MovementResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("maxMovementValue")
    val maxMovementValue: Int,

    @SerializedName("maxMovementUnit")
    val maxMovementUnit: RangeUnitEnum,

    @SerializedName("movementTypeEnum")
    val movementTypeEnum: MovementTypeEnum
)