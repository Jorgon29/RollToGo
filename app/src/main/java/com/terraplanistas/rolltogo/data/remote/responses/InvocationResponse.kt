package com.terraplanistas.rolltogo.data.remote.responses
import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum

data class InvocationResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("durationValue")
    val durationValue: Int,

    @SerializedName("durationUnitEnum")
    val durationUnitEnum: DurationUnitEnum
)