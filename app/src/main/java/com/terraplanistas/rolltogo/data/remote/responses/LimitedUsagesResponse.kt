package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.RecoveryTypeEnum

data class LimitedUsageResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("content")
    val content: ContentResponse,

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