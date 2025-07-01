package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.ActionTypeEnum

data class ActionResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("content")
    val content: ContentResponse,

    @SerializedName("actionType")
    val actionType: ActionTypeEnum,

    @SerializedName("attackFormula")
    val attackFormula: String?,

    @SerializedName("saveAbilityType")
    val saveAbilityType: AbilityTypeEnum?,

    @SerializedName("saveDcFormula")
    val saveDcFormula: String?,

    @SerializedName("isRolled")
    val isRolled: Boolean? = false
)