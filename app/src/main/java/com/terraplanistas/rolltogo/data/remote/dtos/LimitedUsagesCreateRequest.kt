package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.RecoveryTypeEnum

data class LimitedUsageCreateRequest(
    @SerializedName("contentId")
    val contentId: String,

    @SerializedName("baseMaxUsesFormula")
    val baseMaxUsesFormula: String,

    @SerializedName("isScaling")
    val isScaling: Boolean,

    @SerializedName("uses")
    val uses: Int,

    @SerializedName("scalingFormula")
    val scalingFormula: String?,

    @SerializedName("recoveryTypeEnum")
    val recoveryTypeEnum: RecoveryTypeEnum
)
