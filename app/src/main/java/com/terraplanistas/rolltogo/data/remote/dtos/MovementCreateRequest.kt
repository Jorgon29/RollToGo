package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.MovementTypeEnum
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum

data class MovementCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("maxMovementValue")
    val maxMovementValue: Int,

    @SerializedName("maxMovementUnit")
    val maxMovementUnit: RangeUnitEnum,

    @SerializedName("movementTypeEnum")
    val movementTypeEnum: MovementTypeEnum
)