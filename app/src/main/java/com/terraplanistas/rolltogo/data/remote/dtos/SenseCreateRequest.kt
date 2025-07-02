package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum
import com.terraplanistas.rolltogo.data.enums.SensesTypeEnum

data class SenseCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("sensesTypeEnum")
    val sensesTypeEnum: SensesTypeEnum,

    @SerializedName("rangeValue")
    val rangeValue: Int,

    @SerializedName("rangeUnitEnum")
    val rangeUnitEnum: RangeUnitEnum
)