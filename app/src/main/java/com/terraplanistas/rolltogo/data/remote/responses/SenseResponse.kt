package com.terraplanistas.rolltogo.data.remote.responses
import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum
import com.terraplanistas.rolltogo.data.enums.SensesTypeEnum

data class SenseResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("sensesTypeEnum")
    val sensesTypeEnum: SensesTypeEnum,

    @SerializedName("rangeValue")
    val rangeValue: Int,

    @SerializedName("rangeUnitEnum")
    val rangeUnitEnum: RangeUnitEnum
)